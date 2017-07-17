<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Register new Agent</h3>
    </div>
    <div class="panel-body">

        <div class="row">
            <div class="col-sm-4" style="text-align: center;min-height: 350px;">
                <div>
                    <img id="profile-show" src="img/no-image.png" style="cursor: pointer;min-height: 250px;min-width: 250px;max-height: 250px;margin: 25px auto" class="img-thumbnail">
                    <form method="post"  enctype="multipart/form-data" action="uploadPicture" id="picture-upload">
                        <input type="file" onchange="uploadPicture()" id="profile-pic" name="profile" style="position: absolute;top: 0;overflow: hidden;font-size: 200px;left:70px;max-width: 65%;cursor: pointer;opacity: 0">
                    </form>
                </div>
            </div>

            <form id="register-form" action="registerAgent" method="post">
                <input type="hidden" name="profile" id="profile" value="NO-IMG">
                <div class="col-sm-8">
                    <div class="form-group">
                        <label>NIC No.</label>
                        <input class="form-control form-group-sm" type="text" name="nic" placeholder="Eg: 999999999v" max="10" required>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input class="form-control form-group-sm" type="text" name="fname" placeholder="Eg: John" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input class="form-control form-group-sm" type="text" name="lname" placeholder="Eg: Appleseed" required>
                    </div>
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input class="form-control form-group-sm" type="date" name="dob" placeholder="yyyy-mm-dd" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-success" id="register-btn" type="submit">Register</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script type="text/javascript">
    function uploadPicture() {
        if ($('#profile-pic').val()) {
            $("#picture-upload").submit();
        } else {
            $('#profile-show').attr('src', "img/no-image.png");
            $('#profile').val("NO-IMG");
        }
    }
    $(document).ready(function() {
        var options = {
            beforeSend: function() {
                $('#profile-show').attr('src', 'img/loader/ring-alt.gif');
            },
            success: function(data) {
                if (data[0].status === 'success') {
                    $('#profile-show').attr('src', data[0].pic.pic_url);
                    $('#profile').val(data[0].pic.pic_url);
                }
            },
            complete: function() {
            },
            error: function() {
                $('#profile-show').attr('src', "img/no-image.png");
                $('#profile').val("NO-IMG");
            }
        };
        $("#picture-upload").ajaxForm(options);


        var registerOpt = {
            beforeSend: function() {
                $('#register-btn').addClass('disaled');
            },
            success: function(data) {
                if (data === 'OK') {
                    alertify.success('Successfully Registered');
                    loadContent('register_agent.jsp');
                } else {
                    alertify.error('Something went wrong.. !');
                }
            },
            complete: function() {
                $('#register-btn').removeClass('disaled');
            }
        };

        $('#register-form').ajaxForm(registerOpt);

    });
</script>