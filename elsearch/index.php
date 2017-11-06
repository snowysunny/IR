<!DOCTYPE html>
<?php
error_reporting(0);
include 'myhead.php';
?>
<link rel="stylesheet" href="css/common.css">
<script src="js/typeadhead.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-zh-CN.js"></script>
<script src="js/bootstrap-table-filter-control.js"></script>
<script src="js/bootstrap-table-filter.js"></script>
<script src="js/search.js"></script>
<div class="container">
    <div class="row" style="margin-top: 13%">
        <div class="col-lg-8 col-lg-offset-2 text-center" >

            <div class="row" style="margin-bottom: 15px;">
                <div class="text-center" style=""><img src="icon/lgLogo.jpg"></div>
<!--                <div class="text-center" style=""></div>-->
            </div>
            <div class="row">
                <div class="text-center">
                    <div class="form-group" >
                        <form  id="search" action="search.php" method="get" onsubmit="return formsubmit()">
                            <div class="input-group ">
                                <div class="input-group-btn">
                                    <select name="querytype" id="type" class="form-control form-blue" style="width: auto;">
                                        <option value="0">正文</option>
                                        <option value="1">标题</option>
                                        <option value="2">标签</option>
                                        <option value="3">关键字</option>
                                        <!--                                        <option value="4">id</option>-->
                                    </select>
                                </div>
                                <input type="text" name="keyword" id="strquery" autocomplete="off" class="form-control form-blue" maxlength="100" placeholder="请您输入关键词">
                                <span class="input-group-btn">
                                    <button class="btn btn-blue"  id="search_submit" type="submit" >搜索</button>
                                </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="raw">
                <div class="text-center chr-blue">
                    <a href="advancedsearch.php" class="chr-blue">高级检索</a>
                    <span class="chr-blue">|</span>
                    <a href="quadsearch.php" class="chr-blue">二次检索</a>
                    <span class="chr-blue">|</span>
                    <a href="datawatcher.php" class="chr-blue">数据统计</a>
                </div>
            </div>
        </div>
    </div>
</div>
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
<script type="text/javascript">
    function formsubmit() {
        var query = $.trim($("#strquery").val());
        if (query == '' || query == null) {
            return false;
        } else {
            return true;
        }
    }
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
