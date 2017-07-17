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