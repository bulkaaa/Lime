package com.modern.codes.lime.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.UserPOJO;

/**
 * The type Notification service.
 */
@Service
public class NotificationService implements INotificationService {

    /**
     * Instantiates a new Notification service.
     *
     * @param userService     the user service
     * @param resourceService the resource service
     * @param jobService      the job service
     * @param formulaService  the formula service
     */
    @Autowired
    public NotificationService(final UserService userService, final ResourceService resourceService,
                               final IJobService jobService, final IFormulaService formulaService) {
        this.userService = userService;
        this.resourceService = resourceService;
        this.jobService = jobService;
        this.formulaService = formulaService;
    }

    @Override
    public void checkUsedResources() {
        final List<ResourcePOJO> resources = resourceService.findAll();
        resources.forEach(y -> {
            final Double value = y.getQuantity();
            if (value <= y.getCritical_value() && y.getNotifications_on() == true) {
                send(y.getName());

            }
            if (value <= y.getCritical_value() && y.getOrdering_on() == true) {
                sendAutomaticOrder(y);
            }

        });
    }

    @Override
    public Boolean checkAhead(final ResourcePOJO res, final String email, final double use) {
        try {
            if (res.getQuantity() <= res.getCritical_value() && res.getNotifications_on() == true) {

                MailService.SendEmail(email, "Notification from LIME",
                                      "Dear user,\nthe inventory of the Resource you will need to perform the job: "
                                      + res.getName()
                                      + ' '
                                      + "is below its critical value.\nPlease visit LIME application to Order more.");

                return false;
            } else if ((res.getQuantity() - use) <= res.getCritical_value() && res.getNotifications_on() == true) {
                MailService.SendEmail(email, "Notification from LIME",
                                      "Dear user,\nthe inventory of the Resource you will need to perform the job: "
                                      + res.getName()
                                      + ' '
                                      + "will reach its critical value after the current job will be done.\nPlease "
                                      + "visit "
                                      + "LIME application to Order more.");
                return false;
            }
        } catch (final MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void updateUsedResources(final String jobId) {
        final JobPOJO job = jobService.findById(jobId);
        final List<FormulaPOJO> formula = formulaService.findByProductId(job.getPOJOProduct()
                                                                            .getId());
        formula.forEach(y -> {
            final ResourcePOJO resource = y.getPOJOResource();
            Double value = resource.getQuantity();
            value = value - y.getValue();
            resource.setQuantity(value);
        });
        checkUsedResources();
    }

    private Boolean send(final String ResourceName) {
        final List<UserPOJO> users = userService.findAll();
        users.forEach(y -> {
            try {
                MailService.SendEmail(y.getEmailAddress(), "Notification from LIME",
                                      "Dear user, the inventory of the Resource: "
                                      + ResourceName
                                      + " is below its critical value."
                                      + "\nPlease visit LIME Application to order more.");
            } catch (final MessagingException e) {
                LOG.error("FAILED TO SEND MESSAGE", e);
            }
        });
        return true;
    }

    private Boolean sendAutomaticOrder(final ResourcePOJO resource) {
        final Map<ResourcePOJO, Integer> map = new HashMap<>();
        map.put(resource, 30);
        try {
            MailService.SendEmail(resource.getPOJOSupplier()
                                          .getEmailAddress(), "Order from LIME",
                                  MailService.ConstructOrderMsg(resource.getName(), map));
        } catch (final MessagingException e) {
            LOG.error("Failed to send order messages", e);
        }
        return true;
    }

    private final UserService userService;
    private final ResourceService resourceService;
    private final IJobService jobService;
    private final IFormulaService formulaService;
    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

}
