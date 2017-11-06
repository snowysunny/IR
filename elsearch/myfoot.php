</div>   
<footer id="foot" style="background-color: #171616;height: 40px;" role="contentinfo">
    <div class="container">
        <div class="row">
            <div class="col-lg-offset-2 col-md-offset-2 col-lg-8 col-md-8" style="margin-top: 10px">
                    <a  class="col-lg-5  col-md-5 text-right" style="text-decoration:none" ><font color="#FFFFFF">Produced by zzteam.</font></a>
                    <a class="col-lg-2 col-md-2 text-center "  style="text-decoration:none"><font color="#FFFFFF">|</font></a>
                    <a class="col-lg-5 col-md-5 text-left" style="text-decoration:none" g ><font color="#FFFFFF" onclick="alert('先别联系我们。')">联系我们</font></a>
            </div>
        </div>
    </div>
</footer>
    <?php 

                             if ($_SESSION['username']=="")
                          {
                              echo ("<script >$('#LoginModal').modal();</script>");
                          }
                          ?>
    </body>

</html>

