// For Modal
$(function(){
	var appendthis =  ("<div class='modal-overlay js-modal-close'></div>");

		$('a[data-modal-id]').click(function(e) {
			e.preventDefault();
	    $("body").append(appendthis);
	    $(".modal-overlay").fadeTo(500, 0.7);
	    //$(".js-modalbox").fadeIn(500);
			var modalBox = $(this).attr('data-modal-id');
			$('#'+modalBox).fadeIn($(this).data());
		});  
	$(".js-modal-close, .modal-overlay").click(function() {
	    $(".modal-box, .modal-overlay").fadeOut(500, function() {
	        $(".modal-overlay").remove();
	    });
	 
	});
	$(window).resize(function() {
	    $(".modal-box").css({
	        top: ($(window).height() - $(".modal-box").outerHeight()) / 2,
	        left: ($(window).width() - $(".modal-box").outerWidth()) / 2
	    });
	});
	$(window).resize();

	$(".modal-box .modal-body .button .bttn").click(function(){
		$(".modal-overlay").hide();
		$(".modal-overlay:first").show();
	});
	$(".modal-box .modal-body .button .bttn.eduction").click(function(){
		$("#basicinfo").fadeOut(500);
	});
	$(".modal-box .modal-body .button .bttn.myself").click(function(){
		$("#register, #uploadphoto, #basicinfo, #eduction, #basicinfo").fadeOut(500);
	});
	$(".modal-box .modal-body .button .bttn.uploadphoto").click(function(){
		$("#register").fadeOut(500);
	});
	$(".modal-box .modal-body .button .bttn.looking").click(function(){
		$("#myfamilyedu").fadeOut(500);
	});
});

$(document).ready(function(){
	 $("#headerpart .user-pro").click(function(){
	 $("#headerpart ul.notification .drop-down").slideToggle();
	 });

	 // For Search Page
	 $('.searchpage .refinesearch li a').click( function() {
	    var trig = $(this);
	    if ( trig.hasClass('active_a') ) {
	     trig.next('.searchpage .refinesearch li ul.accordingbox').slideToggle();
	     trig.removeClass('active_a');
	    } else {
	     $('.active_a').next('.searchpage .refinesearch li ul.accordingbox').slideToggle();
	     $('.active_a').removeClass('active_a');
	     trig.next('.searchpage .refinesearch li ul.accordingbox').slideToggle();
	     trig.addClass('active_a');
	    };
	    return false;
	 });

	 // For Faq Page
	 $('.faqpage .faqbox .details a').click( function() {
	    var trig = $(this);
	    if ( trig.hasClass('active_a') ) {
	     trig.next('.faqpage .faqbox .details .box').slideToggle();
	     trig.removeClass('active_a');
	    } else {
	     $('.active_a').next('.faqpage .faqbox .details .box').slideToggle();
	     $('.active_a').removeClass('active_a');
	     trig.next('.faqpage .faqbox .details .box').slideToggle();
	     trig.addClass('active_a');
	    };
	    return false;
	 });
	 
	 $("#mobile-menu").click(function(){
		$("nav ul").slideToggle();
	});
	function nav(){
	    if (screen.width > 639) {
	        $("nav ul").css({"display":"block"});
	    }
	    else
	    {
	        $("nav ul").css({"display":"none"});
	    }
	}
	$(window).resize(function() {
		setTimeout(function(){nav();},0);
	});
});