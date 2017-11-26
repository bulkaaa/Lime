
app.controller('CartController', function($scope) {

    $('.minus-btn').on('click', function(e) {
            e.preventDefault();
            var $this = $(this);
            var $input = $this.closest('div').find('input');
            var value = parseInt($input.val());
            if (value > 1) {
                value = value - 1;
            } else {
                value = 0;
            }

            $input.val(value);

        });

        $('.plus-btn').on('click', function(e) {
            e.preventDefault();
            var $this = $(this);
            var $input = $this.closest('div').find('input');
            var value = parseInt($input.val());
            value = value + 1;
            $input.val(value);
        });

        $('.like-btn').on('click', function() {
            $(this).toggleClass('is-active');
        });

        $('.delete-btn').on('click', function() {
            $(this).closest('.item').remove();
        });

    });