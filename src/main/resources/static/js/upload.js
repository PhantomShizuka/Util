$(document).ready(function () {
    var uploadArea = $('#uploadArea');
    var fileInput = $('#file');

    uploadArea.on('click', function () {
        fileInput.click();
    });

    fileInput.on('change', function () {
        var file = fileInput[0].files[0];

        if (file) {
            uploadArea.text(file.name);
        }
    });

    uploadArea.on('dragover', function (e) {
        e.preventDefault();
        e.stopPropagation();
        uploadArea.addClass('dragover');
    });

    uploadArea.on('dragleave', function (e) {
        e.preventDefault();
        e.stopPropagation();
        uploadArea.removeClass('dragover');
    });

    uploadArea.on('drop', function (e) {
        e.preventDefault();
        e.stopPropagation();
        uploadArea.removeClass('dragover');
        var files = e.originalEvent.dataTransfer.files;
        if (files.length > 0) {
            fileInput[0].files = files;
            uploadArea.text(files[0].name);
        }
    });

    $('#uploadForm').on('submit', function (e) {
        e.preventDefault();

        let formData = new FormData(this);
        formData.append('file', fileInput[0].files[0]);

        $.ajax({
            url: '/api/files/upload',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                $('#uploadStatus .alert').removeClass('alert-danger alert-success').addClass('alert-info').text('Subiendo archivo...').parent().removeClass('hidden');
            },
            success: function (response) {
                if (response.success) {
                    $('#uploadStatus .alert').removeClass('alert-info alert-danger').addClass('alert-success').text(response.data).parent().removeClass('hidden');
                    let newFileName = $('#newFileName').val();
                    $('#downloadLink').attr('href', '/api/files/download?fileName=' + newFileName);
                    $('#downloadButton').removeClass('hidden');
                } else {
                    $('#uploadStatus .alert').removeClass('alert-info alert-success').addClass('alert-danger').text(response.data).parent().removeClass('hidden');
                }
            },
            error: function (jqXHR) {
                console.error(jqXHR);
                $('#uploadStatus .alert').removeClass('alert-info alert-success').addClass('alert-danger').text(jqXHR.responseText).parent().removeClass('hidden');
            }
        });
    });
});
