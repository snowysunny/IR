/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ShowpageHtml(id){
    if(id=="")
        return;
  //  $("#strquery").val(query);
    //$("#type").val(type);
    var strurl="http://219.223.251.214:8080/webserver/getJson?query=";
    strurl +=  id;
    strurl += "&querytype=";
    strurl +=  4;
    $.get(strurl,function(data){
      //    var obj = data.replace(/^\s+|\s+$/g, "");
                var obj = $.parseJSON(data);
        var rows = obj.rows;
        if(rows.length==0)
            return ;
        var url= rows[0].url;
        var tag= rows[0].tag;
        var content= rows[0].content;
        var time=rows[0].pubtime;
        var title=rows[0].title;
        $("#mytitle").html(title);
        $("#mycontent").html(content.replace(/\n/g,"<br>&emsp;&emsp;"));
        $("#mysrc").html("<a href="+"'"+url+"'"+">来源："+tag+"</a>");
        $("#mytime").html("发布时间:"+time);
    })

    /*
     //鼠标滚轮翻页
     function handle(delta){
     if (delta > 0){
     if(Page > 1 && Page !== 1){
     GotoPage(Page - 1);
     }
     }
     else{
     if(Page != Pages){
     GotoPage(Page + 1);
     }
     }
     }
     */
    /*function wheel(event){
     var delta = 0;
     if (!event) /!* For IE. *!/
     event = window.event;
     if (event.wheelDelta) { /!* IE或者Opera. *!/
     delta = event.wheelDelta / 120;
     /!** 在Opera9中，事件处理不同于IE
     *!/
     if (window.opera)
     delta = -delta;
     } else if (event.detail) { /!** 兼容Mozilla. *!/
     /!** In Mozilla, sign of delta is different than in IE.
     * Also, delta is multiple of 3.
     *!/
     delta = -event.detail / 3;
     }
     /!** 如果 增量不等于0则触发
     * 主要功能为测试滚轮向上滚或者是向下
     *!/
     if (delta)
     handle(delta);
     }

     /!** 初始化 *!/
     if (window.addEventListener)
     /!** Mozilla的基于DOM的滚轮事件 **!/
     window.addEventListener("DOMMouseScroll", wheel, false);
     /!** IE/Opera. *!/
     window.onmousewheel = document.onmousewheel = wheel;*/


}