var spinnerLoading;
function getAjaxLoading() {
    if (typeof spinnerLoading === 'undefined') {
        spinnerLoading = new Sonic({
            width: 100,
            height: 100,
            stepsPerFrame: 1,
            trailLength: 1,
            pointDistance: .02,
            fps: 30,
            fillColor: '#1E90FF',
            step: function(point, index) {
                this._.beginPath();
                this._.moveTo(point.x, point.y);
                this._.arc(point.x, point.y, index * 7, 0, Math.PI*2, false);
                this._.closePath();
                this._.fill();
            },
            path: [
                ['arc', 50, 50, 30, 0, 360]
            ]
        });
    }
    spinnerLoading.play();
    return spinnerLoading.canvas;
}