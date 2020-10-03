document.addEventListener('DOMContentLoaded', function () {
    //var elems = document.querySelectorAll('select');
    //var instances = M.FormSelect.init(elems, options);
    // Your web app's Firebase configuration
    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
    var firebaseConfig = {
        apiKey: "AIzaSyBgSJL-AVAUYabowb0TlQq6PEH2S71Pong",
        authDomain: "foodorder-a941e.firebaseapp.com",
        databaseURL: "https://foodorder-a941e.firebaseio.com",
        projectId: "foodorder-a941e",
        storageBucket: "foodorder-a941e.appspot.com",
        messagingSenderId: "1043286135132",
        appId: "1:1043286135132:web:bb701ba18c5672bb693199",
        measurementId: "G-8S5YLRRW8E"
    };

    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);

    var files = [];
    let filename = "";

    const imageUpload = document.querySelector("#uploadImage");
    const photo = document.querySelector("#itemPhoto");
    photo.addEventListener("change", getFileUrl);

    imageUpload.addEventListener("click", uploadImage);

    function uploadImage(e) {
        e.preventDefault();
        if (files.length != 0) {
            filename = files[0].name;
            var storage = firebase.storage().ref(filename);
            //update progress bar
            var upload = storage.put(files[0]);
            upload.on(
                "state_changed",
                function progress(snapshot) {
                    var percentage =
                        (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                    document.getElementById("progressBar").value = percentage;
                },
                (error) => console.log("An error occures" + error),
                () => getImageUrl()
            );
        }
    }


    function getFileUrl(e) {
        files.length = 0;
        files = e.target.files;
    }


    function getImageUrl() {
        firebase.storage()
            .ref(filename)
            .getDownloadURL()
            .then(function (url) {
                document.querySelector("#imageUrl").value = url;
            }).catch(function (error) {
            console.log("error encountered", error);
        });
    }

});


// Or with jQuery

$(document).ready(function () {
    $('select').formSelect();
});