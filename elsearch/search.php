<!DOCTYPE html>
<?php
//error_reporting(0);
include 'myhead.php';
?>

<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/bootstrap-table-group-by.css">
<link rel="stylesheet" href="css/bootstrap-table-filter-control.css">
<link rel="stylesheet" href="js/bootstrap-table.css">
<script src="js/typeadhead.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-zh-CN.js"></script>
<script src="js/bootstrap-table-filter-control.js"></script>
<script src="js/bootstrap-table-filter.js"></script>
<!--<script src="js/search.js"></script>-->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 shadow-common" ><!-- 最外面的框 -->
            <div class=" search-common">
                <div class="bg-color center-block" ><!-- main search -->
                    <div class="row">

                        <div class="search-top-pad"></div>
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1 col-sm-10 col-sm-offset-1 text-center">
                            <div class="form-group" >
                                <form id="form1" action="" method="get">
                                    <div class="input-group ">
                                        <div class="input-group-btn">
                                            <select name="querytype" id="type" class="form-control form-white" style="width: auto">
                                                <option value="0">正文</option>
                                                <option value="1">标题</option>
                                                <option value="2">标签</option>
                                                <option value="3">关键字</option>
                                                <!--<option value="4">id</option>-->
                                            </select>
                                        </div>
                                        <input type="text" name="keyword" id="strquery" autocomplete="off" class="form-control form-white" maxlength="100" placeholder="请您输入关键词">
                                        <span class="input-group-btn">
<!--                                        <button class="btn btn-white"  id="search_submit" type="button" onclick="getsearch()">搜索</button>-->
                                            <button class="btn btn-blue"  id="search_submit" type="submit" >搜索</button>
                                    </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
<!--                    <div class="row">-->
<!--                        <div class="text-center chr-white">-->
<!--                            <a href="advancedsearch.php" class="chr-white">高级检索</a>-->
<!--                            <span class="chr-white">|</span>-->
<!--                            <a href="quadsearch.php"  class="chr-white">二次检索</a>-->
<!--                        </div>-->
<!--                    </div>-->

                    <div class="row">
                        <div class="text-center chr-white" id="append">
                        </div>
                    </div>

                    <div class="row">
                        <div class="search-bottom-pad"></div>
                    </div>
                </div>
                <div id="tablediv">
                    <table id="table" style="table-layout:fixed"></table>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">

    var output;
    //从url里获取传递的参数
    function getUrlPara(paraName){
        var sUrl  =  location.href;
        var sReg  =  "(?://?|&){1}"+paraName+"=([^&]*)"
        var re=new RegExp(sReg,"gi");
        re.exec(sUrl);
        return RegExp.$1;
    }

    //从后台php里获取数据

    $.get("Queryjson_2.php", {query: decodeURI(getUrlPara("keyword")),querytype: getUrlPara("querytype")}, function(result){
        var type = $("#type  option:selected").attr('value');
        var data = eval("("+result+")");
        var recommand = data.recommender;
        var suggest = data.suggeter;
        var test1, test2, test3, test4;
        if(suggest.length != 0){
            // $.get("Queryjson_2.php", {query: suggest[0],querytype: 0}, function(result)
            // {

            // }
            // );
            if(suggest.length==4) {
                var test = $('<p class="chr-white">您是否是要搜索以下几个词: &nbsp; &nbsp; &nbsp; </p>').appendTo($('#append'));
                test1 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+suggest[0]+ '"class="chr-white font-weight:bold">'+suggest[0] + '&nbsp;&nbsp;|&nbsp;&nbsp;' +'</a></strong></span>').appendTo(test);
                test2 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+suggest[1]+ '"class="chr-white font-weight:bold">'+suggest[1] + '&nbsp;&nbsp;|&nbsp;&nbsp;' +'</a></strong></span>').appendTo(test1);
                test3 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+suggest[2]+ '"class="chr-white font-weight:bold">'+suggest[2] + '&nbsp;&nbsp;|&nbsp;&nbsp;' +'</a></strong></span>').appendTo(test2);
                $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+suggest[3]+ '"class="chr-white font-weight:bold">'+suggest[3] +'</a></strong></span>').appendTo(test3);
            }
            else if(suggest==0){
            }
        }
        else if(recommand.length != 0){
            if(recommand.length==4) {
                var test = $('<p class="chr-white">为您推荐: &nbsp; &nbsp; &nbsp; </p>').appendTo($('#append'));
                test1 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+recommand[0] + '"class="chr-white font-weight:bold">'+recommand[0] + '&nbsp;&nbsp;|&nbsp;&nbsp;' + '</a></strong></span>').appendTo(test);

                //                test1 = $('<div><a href="search.php?querytype=0&keyword='+recommand[0] + '  '+ '"class="chr-white">'+recommand[0]+'</a></div>');

                test2 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+recommand[1] + '"class="chr-white font-weight:bold">'+recommand[1] + '&nbsp;&nbsp;|&nbsp;&nbsp;' + '</a></strong></span>').appendTo(test1);
                test3 = $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+recommand[2] + '"class="chr-white font-weight:bold">'+recommand[2] + '&nbsp;&nbsp;|&nbsp;&nbsp;' + '</a></strong></span>').appendTo(test2);
                $('<span><strong><a href="search.php?querytype=' + type + '&keyword='+recommand[3]+ '"class="chr-white font-weight:bold">'+recommand[3]+'</a></strong></span>').appendTo(test3);
            }
            else if(recommand==0){
            }
        }

    });



</script>
<script>
    function getsearch()
    {
        var query = $.trim($("#strquery").val());
        var type = $("#type  option:selected").attr('value');
        var data;
        console.log(query);
//        $('#strquery2')[0].value = "";
//        if (query == null || query == "") {
//            //此处留用
//        }
        $.ajax({
            url: "./Queryjson_2.php",
            data: {query: query, querytype: type},
            success: function (e) {
                $('#form1 #search_submit').attr('disabled', false);
                $('#form2 #search_submit').attr('disabled', false);
                var e = e.replace(/^\s+|\s+$/g, "");
                e = $.parseJSON(e);
                e = e.rows;
                data = e;
                $('#table').bootstrapTable("load", data);
            },
            beforeSend: function ()
            {   //触发ajax请求开始时执行
                //$('#advanced-search-form #search_submit').text('搜索中...');
                $('#form1 #search_submit').attr('disabled', true);
                $('#form2 #search_submit').attr('disabled', true);  //改变提交按钮上的文字并将按钮设置为不可点击
            },
            error: function (returndata)
            {
                //$('#advanced-search-form #search_submit').text('搜索');
                $('#form1 #search_submit').attr('disabled', false);
                $('#form2 #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
            }
        });
    }
    $('#table').bootstrapTable({
        dataType:'json',
        //cardView:true,
        sidePagination:'client',
        pagination: true,
        striped: true,
        showFilter:true,
        showColumns: true,
        sortable:false,
        paginationLoop: false,
        //  groupByField:["标签"],
        filterControl:true,//是否显示所有的列
        grouyby:true,
        columns: [{
            formatter: function(value,row,index){
                var url=" <a href='showpage.php?id=" +row.id +"' target='_blank'>" + value + "</a>";;
                return url;
            },
            field: 'title',
            title: '标题',
            width:'100px',
            filterControl:'input'
        },{formatter:
            function(value,row,index){
                if(value.length>150)
                    return value.substring(0,140)+"...."    ;
                else
                    return value;
            },
            field:'content',
            title: '内容',
            width:'300px',
            filterControl:'input'
        },  {
            field: 'pubtime',
            filterControl:'select',
            title: '发布时间',
            width:'80px'
        },{
            field: 'tag',
            title: '标签',
            width:'50px',
            filterControl:'select'
        },
            {
                formatter: function(value,row,index){
                    var url=" <a href='" + value + "'>" +"点击" + "</a> ";
                    return url;
                },
                field: 'url',
                title: '源网站',
                width:'60px'
            }]
    });
</script>
<script>
    var $input = $("#strquery");
    $input.typeahead({
        delay: 500,
        items: 8, //最多显示个数
        source: function (query, process) {
            $.get("./test.php", {query: query}, function (e) {
                var e = e.replace(/^\s+|\s+$/g, "");
                var obj = $.parseJSON(e);
                process(obj);
            });
        }
    });
</script>


<?php
include 'myfoot.php';
?>
<?php
$keyword=$_GET["keyword"];
$querytype=$_GET["querytype"];
if($keyword==null||$keyword=="")
{
    return;
}
else {
    if($querytype==null||$querytype=="")
    {
        $querytype=0;
    }
    echo ('<script>');
    echo ("$('#type').val('".$querytype."');");
    echo ("$('#strquery').val('".$keyword."');");
    echo ("getsearch();");
    echo ('</script>');
}
?>