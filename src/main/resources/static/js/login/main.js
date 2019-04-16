;(function () {

    $("input:first").focus();

    'use strict';

    // Placeholder
    var placeholderFunction = function () {
        $('input, textarea').placeholder({customClass: 'my-placeholder'});
    }

    // Placeholder
    var contentWayPoint = function () {
        var i = 0;
        $('.animate-box').waypoint(function (direction) {

            if (direction === 'down' && !$(this.element).hasClass('animated-fast')) {

                i++;

                $(this.element).addClass('item-animate');
                setTimeout(function () {

                    $('body .animate-box.item-animate').each(function (k) {
                        var el = $(this);
                        setTimeout(function () {
                            var effect = el.data('animate-effect');
                            if (effect === 'fadeIn') {
                                el.addClass('fadeIn animated-fast');
                            } else if (effect === 'fadeInLeft') {
                                el.addClass('fadeInLeft animated-fast');
                            } else if (effect === 'fadeInRight') {
                                el.addClass('fadeInRight animated-fast');
                            } else {
                                el.addClass('fadeInUp animated-fast');
                            }

                            el.removeClass('item-animate');
                        }, k * 200, 'easeInOutExpo');
                    });

                }, 100);

            }

        }, {offset: '85%'});
    };
    // On load
    $(function () {
        placeholderFunction();
        contentWayPoint();

    });

}());

function nextForm(ev) {
    let div = $(ev.target).parents(".myDiv");
    if (!div.find("form").valid())
        return;
    let nextForm = div.next().find("form");
    nextForm.addClass("fadeInLeft");
    nextForm.removeClass("fadeInRight");
    div.next().show();
    nextForm.find("input:first").focus();
    div.hide();
}

function prevForm(ev) {
    let div = $(ev.target).parents(".myDiv");
    let prevForm = div.prev().find("form");
    prevForm.addClass("fadeInRight");
    prevForm.removeClass("fadeInLeft");
    div.prev().show();
    prevForm.find("input:first").focus();
    div.hide();
}function submitForm() {
    let arr = $('#form1').serializeArray();
    arr.push.apply(arr, $('#form2').serializeArray());
    arr.push.apply(arr, $('#form3').serializeArray());
    let formData = new FormData();
    $.each(arr, function (i, e) {
        console.log(formData.append(e.name, e.value));
        console.log(e.name, e.value);
    });
    formData.append("photo",$('#photo')[0].files[0]);
    console.log(formData);
    $.ajax({
        url: '/sign_up',
        type: "post",
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            alert(data);
        }
    })
}
