<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Register Owner</h3>
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
            <div class="col-sm-8">
                <form method="post" action="registerOwner" id="register-form">
                    <input id="profile" name="profile" type="hidden">
                    <legend>Personal Details.</legend>
                    <div class="form-group">
                        <label for="nic">National Identity Card No.</label>
                        <input id="nic" name="nic" type="text" placeholder="999999999V" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="fname">First Name</label>
                        <input id="fname" name="fname" type="text" placeholder="John" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="lname">Last Name</label>
                        <input id="lname" name="lname" type="text" placeholder="Appleseed" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input id="dob" name="dob" type="date" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <legend>Address & Contact Details.</legend>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input id="address" name="address" type="text" placeholder="#13. Main Street, Kuliyapitiya." class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input id="city" name="city" type="text" placeholder="Kuliyapitiya" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="mobile">Mobile No.</label>
                        <input id="mobile" maxlength="12" name="mobile" type="tel" placeholder="+94730000000" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <div class="form-group">
                        <label for="email">E-Mail</label>
                        <input id="email" name="email" type="email" placeholder="email@mail.com" class="form-control" required>
                        <p class="help-block"><span style="color: red">*</span> Required</p>
                    </div>
                    <hr>
                    <button id="register-btn" class="btn btn-success" type="submit">Register</button>
                </form>
            </div>
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
    function resetFields() {
        $('#nic').val('');
        $('#fname').val('');
        $('#lname').val('');
        $('#dob').val('');
        $('#address').val('');
        $('#city').val('');
        $('#mobile').val('');
        $('#email').val('');
        $('#profile-show').attr('src', "img/no-image.png");
        $('#profile').val("NO-IMG");
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
                $('#register-btn').addClass('disabled');
            },
            success: function(data) {
                if (data !== 'error') {
                    if (data === 'ok') {
                        resetFields();
                        alertify.success("Successfully Registered.");
                    } else if (data === 'exists') {
                        alertify.alert("There is an Owner already registered on this NIC. Cannot Registered on this NIC.");
                    }
                } else {
                    alertify.error("Error while Registering.");
                }
            },
            error: function() {
                alertify.alert("Something went wrong!");
            },
            complete: function() {
                $('#register-btn').removeClass('disabled');
            }
        };

        $('#register-form').ajaxForm(registerOpt);

    });
</script>