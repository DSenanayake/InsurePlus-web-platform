<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Insure New Vehicle</h3>
    </div>
    <div class="panel-body" id="wizard-body" style="padding: auto 250px">
        <legend id="wizard-title">Select Owner</legend>
        <div class="row">
            <div class="col-sm-4" style="text-align: center;vertical-align: middle">
                <img id="profile-show" src="img/no-image.png"  style="min-height: 250px;min-width: 250px;max-height: 250px;margin: 25px auto">
            </div>
            <div class="col-sm-8">
                <div class="form-inline">
                    <div class="form-group">
                        <input id="nic" name="nic" onkeyup="findOwner()" placeholder="999999999V" type="text" class="form-control form-group-lg">
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="findOwner()" class="btn btn-success">Find</button>
                    </div>
                </div>
                <div id="owner-details" class="well well-lg" style="margin: 15px auto">
                    <div style="text-align: center">
                        <p>Not found !</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-footer">
        <button id="wiz-back" onclick="wizardPrev()" type="button" class="btn btn-default disabled">Back</button>
        <button id="wiz-next" onclick="wizardNext()" type="button" class="btn btn-info">Next</button>
    </div>
</div>
<script type="text/javascript">
    var nic = null;
    var wizard_index = 0;

    var vehicle = new Object();

    function findOwner() {
        var keyword = $('#nic').val();
        if (keyword) {
            $.post('GetOwnerByNIC', {nic: keyword}, function(data, textStatus, jqXHR) {
                if (textStatus === 'success') {
                    if (data[0].status === 'found') {
                        $('#owner-details').html('<div style="text-align: left">'
                                + '<p><b>Full Name:</b> ' + data[0].owner.fname + ' ' + data[0].owner.lname + '</p>'
                                + '<p><b>Date of Birth:</b> ' + data[0].owner.dob + '</p>'
                                + '<p><b>Address:</b> ' + data[0].owner.address + '</p>'
                                + '<p><b>City:</b> ' + data[0].owner.city + '</p>'
                                + '<p><b>Mobile:</b> ' + data[0].owner.mobile + '</p>'
                                + '<label class="label label-info">' + data[0].owner.status + '</label>'
                                + '</div>');
                        $('#profile-show').attr('src', data[0].owner.profile);
                        nic = data[0].owner.nic;
                    } else {
                        nic = null;
                        $('#owner-details').html('<div style="text-align: center"><p>Not found !</p></div>');
                        $('#profile-show').attr('src', "img/no-image.png");
                    }
                }
            }, 'json');
        } else {
            alertify.alert('Please Enter Owner NIC !');
        }
    }

    function wizardNext() {
        if (wizard_index < 3) {
            wizard_index++;
            $('#wiz-back').removeClass('disabled');
        }
        if (wizard_index === 1) {
            if (nic !== null) {
                loadWizardContent('res/vehicle wizard/vehicle_details.jsp', function() {
                    $('#wiz-next').html('Register');
                    $('#ownerNic').val(nic);
                });
            } else {
                alertify.alert('Please Select an Owner !');
                wizard_index = 0;
            }
        } else if (wizard_index === 2) {
            $('#vehicleRegForm').submit();
        }
    }
    function wizardPrev() {
        if (wizard_index > 0) {
            wizard_index--;
            $('#wiz-back').removeClass('disabled');
        } else {
            $('#wiz-back').addClass('disabled');
        }

        if (wizard_index === 0) {
            loadWizardContent('res/vehicle wizard/owner_details.jsp', function() {
                $('#nic').val(nic);
                findOwner();
            });
        } else if (wizard_index === 1) {

        }

    }
    function loadWizardContent(url, callback) {
        $.post(url, {}, function(data) {
            $('#wizard-body').html(data);
            callback();
        }, 'html');
    }

   
</script>