/**
 * Created by hadoop on 2017/04/05.
 */
var strHtml = "";
$(function(){
    $("#btn").click(function(){
        var strurl="http://localhost:8080/getJson?query=常州";
        strurl +=  $("#strquery").val();
        $.getJSON(strurl,function(data){
            var $jsontip = $("#jsonTip");
            var intHtml = 0;//存储数据的变量src="js/jquery-1.8.3.min.js"
            intHtml = data.rows.length;
            var index=0;
            strHtml = "{\"total\":" + intHtml +",\"rows\": [";
            $.each(data.rows,function(infoIndex,info){
                strHtml += "{\"id\":"+info["id"]+",";
                strHtml += "\"title\":\""+info["title"]+"\",";
                strHtml += "\"time\":\""+info["time"]+"\",";
                strHtml += "\"content\":\""+info["content"]+"\",";
                strHtml += "\"url\":\"" + info["url"] + "\"}";
                index++;
                if(index < intHtml)
                    strHtml += ",";
            })
            strHtml += "]}";
            $jsontip.html(strHtml);//显示处理后的数据
        })
    })
});

function $(id){
    return document.getElementById(id);
} //定义获取ID的方法