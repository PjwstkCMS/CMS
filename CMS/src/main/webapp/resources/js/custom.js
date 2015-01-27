$(document).ready(function() {
   

    $("#toggle-zasoby").click(function(){
        var $target = $('#zarzadzanie-zasobami');
        $target.slideToggle( 400, function () {

        });
        $(this).find('i').toggleClass('icon-arrow-down-ico icon-arrow-right-ico');

    });

    $("#toggle-konfiguracja").click(function(){
        var $target = $('#konfiguracja-systemu');
        $target.slideToggle( 400, function () {

        });
        $(this).find('i').toggleClass('icon-arrow-down-ico icon-arrow-right-ico');

    });

    /* off-canvas sidebar toggle */

    $('[data-toggle=offcanvas]').click(function() {
        $(this).toggleClass('visible-xs text-center');

        $('.row-offcanvas').toggleClass('active');
        $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
        $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
        $('#btnShow').toggle();
    });






    (function($){
        $(window).load(function(){

            /* all available option parameters with their default values */
            $("#sidebar").mCustomScrollbar({
                setWidth:false,
                setHeight:false,
                setTop:0,
                setLeft:0,
                axis:"y",
                scrollbarPosition:"inside",
                scrollInertia:300,
                autoDraggerLength:true,
                autoHideScrollbar:false,
                autoExpandScrollbar:false,
                alwaysShowScrollbar:0,
                snapAmount:null,
                snapOffset:0,
                mouseWheel:{
                    enable:true,
                    scrollAmount:"auto",
                    axis:"y",
                    preventDefault:false,
                    deltaFactor:"auto",
                    normalizeDelta:false,
                    invert:false,
                    disableOver:["select","option","keygen","datalist","textarea"]
                },
                scrollButtons:{
                    enable:false,
                    scrollType:"stepless",
                    scrollAmount:"auto"
                },
                keyboard:{
                    enable:true,
                    scrollType:"stepless",
                    scrollAmount:"auto"
                },
                contentTouchScroll:25,
                advanced:{
                    autoExpandHorizontalScroll:false,
                    autoScrollOnFocus:"input,textarea,select,button,datalist,keygen,a[tabindex],area,object,[contenteditable='true']",
                    updateOnContentResize:true,
                    updateOnImageLoad:true,
                    updateOnSelectorChange:false,
                    releaseDraggableSelectors:false
                },
                theme:"minimal-dark",
                callbacks:{
                    onInit:false,
                    onScrollStart:false,
                    onScroll:false,
                    onTotalScroll:false,
                    onTotalScrollBack:false,
                    whileScrolling:false,
                    onTotalScrollOffset:0,
                    onTotalScrollBackOffset:0,
                    alwaysTriggerOffsets:true,
                    onOverflowY:false,
                    onOverflowX:false,
                    onOverflowYNone:false,
                    onOverflowXNone:false
                },
                live:false,
                liveSelector:null
            });

        });
    })(jQuery);


    (function($){
        $(window).load(function(){

            /* all available option parameters with their default values */
            $("#main").mCustomScrollbar({
                setWidth:false,
                setHeight:false,
                setTop:0,
                setLeft:0,
                axis:"y",
                scrollbarPosition:"inside",
                scrollInertia:300,
                autoDraggerLength:true,
                autoHideScrollbar:false,
                autoExpandScrollbar:false,
                alwaysShowScrollbar:0,
                snapAmount:null,
                snapOffset:0,
                mouseWheel:{
                    enable:true,
                    scrollAmount:"auto",
                    axis:"y",
                    preventDefault:false,
                    deltaFactor:"auto",
                    normalizeDelta:false,
                    invert:false,
                    disableOver:["select","option","keygen","datalist","textarea"]
                },
                scrollButtons:{
                    enable:false,
                    scrollType:"stepless",
                    scrollAmount:"auto"
                },
                keyboard:{
                    enable:true,
                    scrollType:"stepless",
                    scrollAmount:"auto"
                },
                contentTouchScroll:25,
                advanced:{
                    autoExpandHorizontalScroll:false,
                    autoScrollOnFocus:"input,textarea,select,button,datalist,keygen,a[tabindex],area,object,[contenteditable='true']",
                    updateOnContentResize:true,
                    updateOnImageLoad:true,
                    updateOnSelectorChange:false,
                    releaseDraggableSelectors:false
                },
                theme:"minimal-dark",
                callbacks:{
                    onInit:false,
                    onScrollStart:false,
                    onScroll:false,
                    onTotalScroll:false,
                    onTotalScrollBack:false,
                    whileScrolling:false,
                    onTotalScrollOffset:0,
                    onTotalScrollBackOffset:0,
                    alwaysTriggerOffsets:true,
                    onOverflowY:false,
                    onOverflowX:false,
                    onOverflowYNone:false,
                    onOverflowXNone:false
                },
                live:false,
                liveSelector:null
            });

        });
    })(jQuery);
    
    
    

});

/**
 * Created by pawelek on 2014-11-05.
 */
$(document).on('ready', function() {

    $(window).on('resize', function() {

        var parentwidth;
        parentwidth = $("#sidebar").outerWidth(true);


        if (parentwidth <= 60) {
            $("#xs-menu").find(".sub-ico-align").css(
                {
                    "margin-left" : (parentwidth / 4) + 1
                }
            );
        }
        else {
            $("#xs-menu").find(".sub-ico-align").css(
                {
                    "margin-left" : (parentwidth / 3) + 4
                }
            );

        }

        //console.log(parentwidth);
    }).trigger('resize'); // Trigger resize handlers.

});//ready




