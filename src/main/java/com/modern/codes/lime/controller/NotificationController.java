package com.modern.codes.lime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.IFormulaService;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.ResourceService;
import com.modern.codes.lime.service.UserService;
import com.modern.codes.lime.tools.MailTools;

@RestController()
@RequestMapping(path = "/notification")
public class NotificationController {

    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    IJobService jobService;
    @Autowired
    IFormulaService formulaService;

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

    public void checkUsedResources() {
        final List<ResourcePOJO> resources = resourceService.findAll();
        resources.forEach(y -> {
            final Double value = y.getQuantity();
            if (value == y.getCritical_value() && y.getNotifications_on() == true) {
                send(y.getName());
            }
        });
    }

    public static Boolean checkAhead(final ResourcePOJO res, final String email, final double use) {
        if (res.getQuantity() <= res.getCritical_value() && res.getNotifications_on() == true) {
            MailTools.SendEmail(email, "Notification from LIME",
                                "Dear user,\nthe inventory of the Resource you will need to perform the job: "
                                + res.getName()
                                + ' '
                                + "is below its critical value.\nPlease visit LIME application to Order more.");
            return false;
        } else if ((res.getQuantity() - use) <= res.getCritical_value() && res.getNotifications_on() == true) {
            MailTools.SendEmail(email, "Notification from LIME",
                                "Dear user,\nthe inventory of the Resource you will need to perform the job: "
                                + res.getName()
                                + ' '
                                + "will reach its critical value after the current job will be done.\nPlease visit "
                                + "LIME application to Order more.");
            return false;

        } else {
            return true;
        }
    }

    private Boolean send(final String ResourceName) {
        final List<UserPOJO> users = userService.findAll();
        users.forEach(y -> {
            MailTools.SendEmail(y.getEmailAddress(), "Notification from LIME",
                                "Dear user, the inventory of the Resource: "
                                + ResourceName
                                + " is below its critical value."
                                + "\nPlease visit LIME Application to order more.");
        });
        return true;
    }
}
