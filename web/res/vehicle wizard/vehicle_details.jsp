<script type="text/javascript">
    $(document).ready(function() {
        var registerOpt = {
            beforeSend: function() {
                $('#wiz-next').addClass('disabled');
            },
            success: function(data) {
                if (data !== 'ERROR') {
                    if (data === 'OK') {
                        alertify.success("Successfully Registered.");
                        loadContent('res/register_vehicle.jsp');
                    } else if (data === 'EMPTY') {
                        wizard_index = 2;
                        alertify.alert('You have to fill al <b>fields !</b>');
                    }
                } else {
                    wizard_index = 2;
                    alertify.error("Error while Registering.");
                }
            },
            error: function() {
                wizard_index = 2;
                alertify.alert("Something went wrong!");
            },
            complete: function() {
                $('#wiz-next').removeClass('disabled');
            }
        };

        $('#vehicleRegForm').ajaxForm(registerOpt);
    });
</script>
<legend id="wizard-title">Vehicle Details</legend>
<!--<div class="row">-->
<form action="RegisterVehicle" method="post" id="vehicleRegForm">
    <div class="form-group">
        <label>Vehicle No</label>
        <input class="form-control" type="text" id="vehicleNo" name="vehicleNo" placeholder="Eg: CAF-0000" required>
    </div>
    <div class="form-group">
        <label>Vehicle Type</label>
        <select class="form-control" id="insuraceType" name="insuraceType" required>
            <option value="Car">Car</option>
            <option value="Van">Van</option>
            <option value="SUV">SUV</option>
            <option value="Lorry">Lorry</option>
            <option value="Bus">Bus</option>
            <option value="Motorcycle">Motorcycle</option>
        </select></div>
    <div class="form-group">
        <label>Vehicle Brand</label>
        <input class="form-control" type="text" id="brand" name="brand" placeholder="Eg: Toyota" required>
    </div>
    <div class="form-group">
        <label>Vehicle Model</label>
        <input class="form-control" type="text" id="model" name="model" placeholder="Eg: Corolla" required>
    </div>
    <div class="form-group">
        <label>Color</label>
        <input class="form-control" type="text" id="color" name="color" placeholder="Eg: White" required>
    </div>
    <div class="form-group">
        <label>Year of Made</label>
        <input class="form-control" type="number" value="2015" id="year" name="year" placeholder="Eg: 2015" required>
    </div>
    <div class="form-group">
        <label>Engine No</label>
        <input class="form-control" type="text" id="engin" name="engineNo" required>
    </div>
    <div class="form-group">
        <label>Chasis No</label>
        <input class="form-control" type="text" id="chasis" name="chasisNo" required>
    </div>

    <legend>Insurance Coverage</legend>
    <div class="form-group">
        <label>Insurance Type</label>
        <select class="form-control" id="insuraceType" name="insuranceType" required>
            <option value="1">Full Insurance</option>
            <option value="2">3rd Party Insurance</option>
        </select>
    </div>
    <div class="form-group">
        <label>Insured Date</label>
        <input class="form-control" type="date" id="insuredDate" name="insuredDate" required>
    </div>
    <div class="form-group">
        <label>Expire Date</label>
        <input class="form-control" type="date" id="expireDate" name="expireDate" required>
    </div>
    <div class="form-group">
        <label>Insurance Period</label>
        <br>
        <div class="col-sm-10">
            <input class="form-control" type="number" id="insuracePeriod" name="period" required>
        </div>
        <div class="col-sm-2">
            <select class="form-control" name="periodType">
                <option value="1">Year(s)</option>
                <option value="2">Months(s)</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label>Coverage Amount</label>
        <input class="form-control" type="number" id="amount" name="amount" required>
    </div>
    <input type="hidden" id="ownerNic" name="owner">
</form>
<!--</div>-->