<!DOCTYPE html>
<?php
//快照
error_reporting(0);
include 'myhead.php';
?>     
<script src="js/showpage.js" ></script>
<style>
    .shadowtent{
 box-shadow: 0px -1px 3px  3px #e1dcdc;

    }
     .shadowtitle{
 box-shadow: 0px 0px 1px  1px #e1dcdc;
 margin-top: 20px;
  margin-bottom:50px;
    }
</style>
<div class="container" >
    <div class="row" style="margin-top: 60px">
        <div class="col-lg-offset-2 col-md-offset-2 col-lg-8 col-md-8 shadowtent" >
            <div class="col-lg-offset-1 col-md-offset-1 col-lg-10  col-lg-10 shadowtitle">
                <div class="text-center">
                    <h3 id="mytitle" >title</h3>
                </div>
                <div class="row" style="margin-bottom: 20px">
                    <h5  id="mysrc" class="col-lg-offset-1 col-md-offset-1 col-lg-5  col-md-5 text-left" >来源</h5>
                    <h5 id="mytime" class="col-md-5   col-lg-5 text-right">发布时间</h5>
                </div>
                </div>
            <div class="row"><div class="col-lg-offset-1 col-md-offset-1 col-lg-10  col-lg-10"><p id="mycontent" >123</p></div></div>
        </div>



    </div>
</div>
           <?php echo "<script type='text/javascript'>ShowpageHtml('".$_GET["id"]."');</script>"; ?>
       
<?php
include 'myfoot.php';
?>