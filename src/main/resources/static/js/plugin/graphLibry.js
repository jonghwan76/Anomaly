//노선 데이터
var data_station = {
    pointData: [
        {rcode:"1_0",code:"1_0", point: true,label: '인천공항2터미널', color: '#fff'},
        {rcode:"2_6",code:"1_1", point: false,label: '', color: '#fff'},
        {rcode:"2_5",code:"1_2", point: false,label: '', color: '#fff'},
        {rcode:"2_4",code:"1_3", point: false,label: '', color: '#fff'},
        {rcode:"2_3",code:"1_4", point: false,label: '', color: '#fff'},
        {rcode:"2_2",code:"1_5", point: false,label: '', color: '#fff'},
        {rcode:"2_1",code:"1_6", point: false,label: '', color: '#fff'},
        {rcode:"2_0",code:"2_0", point: true,label: '인천공항1터미널', color: '#fff'},
        {rcode:"3_6",code:"2_1", point: false,label: '', color: '#fff'},
        {rcode:"3_5",code:"2_2", point: false,label: '', color: '#fff'},
        {rcode:"3_4",code:"2_3", point: false,label: '', color: '#fff'},
        {rcode:"3_3",code:"2_4", point: false,label: '', color: '#fff'},
        {rcode:"3_2",code:"2_5", point: false,label: '', color: '#fff'},
        {rcode:"3_1",code:"2_6", point: false,label: '', color: '#fff'},
        {rcode:"3_0",code:"3_0", point: true,label: '공항화물청사', color: '#fff'},
        {rcode:"4_6",code:"3_1", point: false,label: '', color: '#fff'},
        {rcode:"4_5",code:"3_2", point: false,label: '', color: '#fff'},
        {rcode:"4_4",code:"3_3", point: false,label: '', color: '#fff'},
        {rcode:"4_3",code:"3_4", point: false,label: '', color: '#fff'},
        {rcode:"4_2",code:"3_5", point: false,label: '', color: '#fff'},
        {rcode:"4_1",code:"3_6", point: false,label: '', color: '#fff'},
        {rcode:"4_0",code:"4_0", point: true,label: '운서', color: '#fff'},
        {rcode:"5_6",code:"4_1", point: false,label: '', color: '#fff'},
        {rcode:"5_5",code:"4_2", point: false,label: '', color: '#fff'},
        {rcode:"5_4",code:"4_3", point: false,label: '', color: '#fff'},
        {rcode:"5_3",code:"4_4", point: false,label: '', color: '#fff'},
        {rcode:"5_2",code:"4_5", point: false,label: '', color: '#fff'},
        {rcode:"5_1",code:"4_6", point: false,label: '', color: '#fff'},
        {rcode:"5_0",code:"5_0", point: true,label: '영종', color: '#fff'},
        {rcode:"6_12",code:"5_1", point: false,label: '', color: '#fff'},
        {rcode:"6_11",code:"5_2", point: false,label: '', color: '#fff'},
        {rcode:"6_10",code:"5_3", point: false,label: '', color: '#fff'},
        {rcode:"6_9",code:"5_4", point: false,label: '', color: '#fff'},
        {rcode:"6_8",code:"5_5", point: false,label: '', color: '#fff'},
        {rcode:"6_7",code:"5_6", point: false,label: '', color: '#fff'},
        {rcode:"6_6",code:"5_7", point: false,label: '', color: '#fff'},
        {rcode:"6_5",code:"5_8", point: false,label: '', color: '#fff'},
        {rcode:"6_4",code:"5_9", point: false,label: '', color: '#fff'},
        {rcode:"6_3",code:"5_10", point: false,label: '', color: '#fff'},
        {rcode:"6_2",code:"5_11", point: false,label: '', color: '#fff'},
        {rcode:"6_1",code:"5_12", point: false,label: '', color: '#fff'},
        {rcode:"6_0",code:"6_0", point: true,label: '청라국제도시', color: '#fff'},
        {rcode:"7_6",code:"6_1", point: false,label: '', color: '#fff'},
        {rcode:"7_5",code:"6_2", point: false,label: '', color: '#fff'},
        {rcode:"7_4",code:"6_3", point: false,label: '', color: '#fff'},
        {rcode:"7_3",code:"6_4", point: false,label: '', color: '#fff'},
        {rcode:"7_2",code:"6_5", point: false,label: '', color: '#fff'},
        {rcode:"7_1",code:"6_6", point: false,label: '', color: '#fff'},
        {rcode:"7_0",code:"7_0", point: true,label: '검암', color: '#fff'},
        {rcode:"8_6",code:"7_1", point: false,label: '', color: '#fff'},
        {rcode:"8_5",code:"7_2", point: false,label: '', color: '#fff'},
        {rcode:"8_4",code:"7_3", point: false,label: '', color: '#fff'},
        {rcode:"8_3",code:"7_4", point: false,label: '', color: '#fff'},
        {rcode:"8_2",code:"7_5", point: false,label: '', color: '#fff'},
        {rcode:"8_1",code:"7_6", point: false,label: '', color: '#fff'},
        {rcode:"8_0",code:"8_0", point: true,label: '계양', color: '#fff'},
        {rcode:"9_6",code:"8_1", point: false,label: '', color: '#fff'},
        {rcode:"9_5",code:"8_2", point: false,label: '', color: '#fff'},
        {rcode:"9_4",code:"8_3", point: false,label: '', color: '#fff'},
        {rcode:"9_3",code:"8_4", point: false,label: '', color: '#fff'},
        {rcode:"9_2",code:"8_5", point: false,label: '', color: '#fff'},
        {rcode:"9_1",code:"8_6", point: false,label: '', color: '#fff'},
        {rcode:"9_0",code:"9_0", point: true,label: '김포공항', color: '#fff'},
        {rcode:"10_6",code:"9_1", point: false,label: '', color: '#fff'},
        {rcode:"10_5",code:"9_2", point: false,label: '', color: '#fff'},
        {rcode:"10_4",code:"9_3", point: false,label: '', color: '#fff'},
        {rcode:"10_3",code:"9_4", point: false,label: '', color: '#fff'},
        {rcode:"10_2",code:"9_5", point: false,label: '', color: '#fff'},
        {rcode:"10_1",code:"9_6", point: false,label: '', color: '#fff'},
        {rcode:"10_0",code:"10_0", point: true,label: '마곡나루', color: '#fff'},
        {rcode:"11_14",code:"10_1", point: false,label: '', color: '#fff'},
        {rcode:"11_13",code:"10_2", point: false,label: '', color: '#fff'},
        {rcode:"11_12",code:"10_3", point: false,label: '', color: '#fff'},
        {rcode:"11_11",code:"10_4", point: false,label: '', color: '#fff'},
        {rcode:"11_10",code:"10_5", point: false,label: '', color: '#fff'},
        {rcode:"11_9",code:"10_6", point: false,label: '', color: '#fff'},
        {rcode:"11_8",code:"10_7", point: false,label: '', color: '#fff'},
        {rcode:"11_7",code:"10_8", point: false,label: '', color: '#fff'},
        {rcode:"11_6",code:"10_9", point: false,label: '', color: '#fff'},
        {rcode:"11_5",code:"10_10", point: false,label: '', color: '#fff'},
        {rcode:"11_4",code:"10_11", point: false,label: '', color: '#fff'},
        {rcode:"11_3",code:"10_12", point: false,label: '', color: '#fff'},
        {rcode:"11_2",code:"10_13", point: false,label: '', color: '#fff'},
        {rcode:"11_1",code:"10_14", point: false,label: '', color: '#fff'},
        {rcode:"11_0",code:"11_0", point: true,label: '디지털미디어시티', color: '#fff'},
        {rcode:"12_6",code:"11_1", point: false,label: '', color: '#fff'},
        {rcode:"12_5",code:"11_2", point: false,label: '', color: '#fff'},
        {rcode:"12_4",code:"11_3", point: false,label: '', color: '#fff'},
        {rcode:"12_3",code:"11_4", point: false,label: '', color: '#fff'},
        {rcode:"12_2",code:"11_5", point: false,label: '', color: '#fff'},
        {rcode:"12_1",code:"11_6", point: false,label: '', color: '#fff'},
        {rcode:"12_0",code:"12_0", point: true,label: '홍대입구', color: '#fff'},
        {rcode:"13_6",code:"12_1", point: false,label: '', color: '#fff'},
        {rcode:"13_5",code:"12_2", point: false,label: '', color: '#fff'},
        {rcode:"13_4",code:"12_3", point: false,label: '', color: '#fff'},
        {rcode:"13_3",code:"12_4", point: false,label: '', color: '#fff'},
        {rcode:"13_2",code:"12_5", point: false,label: '', color: '#fff'},
        {rcode:"13_1",code:"12_6", point: false,label: '', color: '#fff'},
        {rcode:"13_0",code:"13_0", point: true,label: '공덕', color: '#fff'},
        {rcode:"14_6",code:"13_1", point: false,label: '', color: '#fff'},
        {rcode:"14_5",code:"13_2", point: false,label: '', color: '#fff'},
        {rcode:"14_4",code:"13_3", point: false,label: '', color: '#fff'},
        {rcode:"14_3",code:"13_4", point: false,label: '', color: '#fff'},
        {rcode:"14_2",code:"13_5", point: false,label: '', color: '#fff'},
        {rcode:"14_1",code:"13_6", point: false,label: '', color: '#fff'},
        {rcode:"14_0",code:"14_0", point: true,label: '서울역', color: '#fff'}
    ]
};

var moniteringItem = {
    init: function(item,_data){
        var centerTxt = "<span>22건</span>";
        var width = 128;
        var height = 128;

        var margin = {
            left: 0,
            right: 0,
            bottom: 0,
            top: 0
        };
        var cWidth = width - margin.left - margin.right;
        var cHeight = height - margin.top - margin.bottom;
        var radius = Math.min(cWidth, cHeight) / 2;

        var svg = d3
            .select(item)
            .attr("width", width)
            .attr("height", height);
        var g = svg
            .append("g")
            .attr("transform", "translate(" + cWidth / 2 + "," + cHeight / 2 + ")")
            .attr("class", "chart-group");

        var donutWidth = 55;

        // 원호의 외경과 내경을 정의
        arc = d3.arc()
            .innerRadius(donutWidth)
            .outerRadius(radius);
        g.append('image')
            .attr('href', "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' width='20px' height='16px'%3E%3Cpath fill-rule='evenodd' fill='rgb(68, 68, 68)' d='M19.748,5.981 C19.598,6.108 19.406,6.171 19.216,6.171 C19.006,6.171 18.798,6.095 18.644,5.946 C16.374,3.745 13.302,2.534 9.994,2.534 C8.816,2.534 7.672,2.696 6.580,2.994 L5.343,1.883 C6.811,1.391 8.374,1.121 9.993,1.121 C13.741,1.121 17.219,2.493 19.787,4.983 C20.081,5.268 20.064,5.715 19.748,5.981 ZM16.363,9.103 C16.162,9.103 15.961,9.033 15.808,8.894 C14.373,7.587 12.567,6.812 10.663,6.662 L9.127,5.282 C9.416,5.258 9.702,5.219 9.993,5.219 C12.560,5.219 15.019,6.171 16.918,7.899 C17.222,8.176 17.220,8.623 16.913,8.898 C16.761,9.035 16.562,9.103 16.363,9.103 ZM7.916,5.683 L9.279,6.915 L16.847,13.701 C17.182,14.004 17.182,14.496 16.847,14.799 C16.511,15.103 15.966,15.103 15.631,14.799 L12.051,11.617 C11.413,11.240 10.661,11.014 9.986,11.014 C9.986,11.014 9.986,11.014 9.986,11.014 L9.929,11.014 L9.929,11.014 C9.010,11.014 7.949,11.433 7.225,12.081 C7.073,12.217 6.874,12.285 6.675,12.285 C6.474,12.285 6.273,12.215 6.120,12.076 C5.816,11.799 5.819,11.352 6.125,11.077 C7.115,10.191 8.522,9.632 9.823,9.603 L7.296,7.320 C6.154,7.698 5.091,8.316 4.179,9.147 C3.875,9.424 3.380,9.426 3.074,9.151 C2.767,8.876 2.766,8.429 3.069,8.152 C3.969,7.333 4.995,6.689 6.098,6.237 L3.943,4.291 C2.997,4.804 2.123,5.443 1.344,6.199 C1.049,6.484 0.555,6.500 0.239,6.234 C-0.077,5.968 -0.094,5.521 0.200,5.236 C0.990,4.471 1.865,3.811 2.808,3.264 L0.644,1.297 C0.309,0.993 0.309,0.501 0.644,0.198 C0.980,-0.105 1.524,-0.105 1.860,0.198 L4.377,2.485 L5.566,3.559 L7.916,5.683 ZM9.955,13.656 C10.667,13.656 11.244,14.178 11.244,14.821 C11.244,15.465 10.667,15.987 9.955,15.987 C9.243,15.987 8.665,15.465 8.665,14.821 C8.665,14.178 9.243,13.656 9.955,13.656 Z'/%3E%3C/svg%3E")
            .attr('x','-10')
            .attr('y','-53')
            .attr('class', 'wifiOff');


        g.append('text')
            .attr('fill', 'white')
            .attr("dy", "-.8em")
            .attr("class", "level")
            .style("text-anchor", "middle")
            .style("font-size", "16px")
            .text(' ');
        g.append('text')
            .attr('fill', '#444')
            .attr("dy", "2.6em")
            .attr("class", "errNum")
            .style("text-anchor", "middle")
            .style("font-size", "16px")
            .text('');

        // Pi 정의
        var pie = d3.pie()
            .sort(null)
            .value(function(d) {
                return d.val;
            });

        pieGroup = g
            .selectAll(".arc")
            .data(pie(_data.graphData))
            .enter()
            .append("g")
            .attr("class", "arc")
            .style("transform", "rotate(-25deg)");

        var tooltip = d3.select("body").append("div")
            .attr("class", "toolTip")
            .style("display", "none");


        function arcColor(d){
            var arcColor;
            if(d == 0){
                arcColor = '#e0e4ed';
            }else if(d == 1){
                arcColor = '#97e017';
            }else if(d == 2){
                arcColor = '#ff902f';
            }else if(d == 3){
                arcColor = '#ff0000';
            }else if(d == 'none'){
                arcColor = '#dedede';
            }
            return arcColor;
        }


        function statsTxt(d){
            var txt;
            if(d == 1){
                txt = '정상';
            }else if(d == 2){
                txt = '경고';
            }else if(d == 3){
                txt = '이상';
            }
            return txt;
        }

        pieGroup
            .append("path")
            .attr("d", arc)
            .style('pointer-events', function(d){
                if(d.data.stats == 0 || d.data.stats == 'none'){
                    return 'none'
                }
            })
            .style("fill", function(d) {
                return arcColor(d.data.stats)
            })
            .style("stroke", "#ebeef7")
            // .on("click", function(){location.href = "../operating/train_detail.html"})//클릭시 페이지이동
            .on("mouseover", function() { tooltip.style("display", null); })
            .on("mouseout",  function() { tooltip.style("display", "none"); })
            .on("mousemove", function(d) {
                tooltip.style("left", (d3.event.pageX + 10) + "px");
                tooltip.style("top", (d3.event.pageY - 10) + "px");
                tooltip.html('<span class="rect" style="background:' + arcColor(d.data.stats) + '"></span>' + d.data.tip + ' <span class="errTxt">'+ statsTxt(d.data.stats) + '</span>');
            });
    },
    redraw: function(item,_data){
        var svg = d3.select(item);
        svg.selectAll('.arc').select('path').each(function(d, i) {
            d3.select(this).style('fill', function(d){return arcColor(_data.graphData[i].stats)})
        });

        function arcColor(d){
            var arcColor;
            if(d == 0){
                arcColor = '#e0e4ed';
            }else if(d == 1){
                arcColor = '#97e017';
            }else if(d == 2){
                arcColor = '#ff902f';
            }else if(d == 3){
                arcColor = '#ff0000';
            }else if(d == 'none'){
                arcColor = '#dedede';
            }
            return arcColor;
        }
    }
};


var donutGraph = {
    init: function(obj,_data){
        var width = 200;
        var height = 200;

        var margin = {
            left: 0,
            right: 0,
            bottom: 0,
            top: 0,
        };
        var cWidth = width - margin.left - margin.right;
        var cHeight = height - margin.top - margin.bottom;
        var radius = Math.min(cWidth, cHeight) / 2;

        var svg = d3.select(obj)
            .attr("width", width)
            .attr("height", height);

        var g = svg.append("g")
            .attr("transform","translate(" + (cWidth / 2) + "," + (cHeight / 2) +")")
            .attr("class","chart-group");

        var donutWidth = 65;

        // 円弧の外径と内径を定義
        var arc = d3.arc()
            .innerRadius(donutWidth)
            .outerRadius(radius);

        // Pi정의
        var pie = d3.pie()
            .sort(null)
            .value(function(d) {
                return d.val;
            });
        g.append('text')
            .attr('fill', '#333')
            .attr("dy", ".35em")
            .attr("class", "centerTxt")
            .style("text-anchor", "middle")
            .style("font-size", "40px")
            .text(_data.cenTxt);

        var pieGroup = g.selectAll(".arc")
            .data(pie(_data.graphData))
            .enter().append("g")
            .attr("class", "arc");

        pieGroup.append("path")
            .attr("d", arc)
            .style("fill", function(d) {
                return d.data.color;
            })
            .transition()
            .duration(1000)
            .attrTween("d", function(d) {
                var interpolate = d3.interpolate(
                    {startAngle: 0, endAngle: 0},
                    {startAngle: d.startAngle, endAngle : d.endAngle}
                );
                return function(t) {
                    return arc(interpolate(t))
                }
            });


        pieGroup.append('text')
            .attr('fill', 'white')
            .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
            .attr("dy", ".20em")
            .style("text-anchor", "middle")
            .style("font-size", "12px")
            .text(function(d) { return d.data.label; });
        svg.append("rect").html('test')
    }
};

"use strict";

var lineGraphYMax = 500;

//lineGraph
var lineGraph = {
    init: function init(obj, jsonData) {
        var dataKeys = Object.keys(jsonData[0]);

        //const mapData = jsonData.map(function(d, i){ console.log(d), console.log({...d}) });

        var mapData = jsonData.map(function(d, i){ return  ({time: i, d:d}) });



        var idleTimeout = null;
        var datums = dataKeys.map(function (key) {
            return jsonData.map(function (d, i) {
                return {
                    time: i,
                    value: d[key]
                };
            });
        });

        var margin = {
            top: 10,
            right: 90,
            bottom: 40,
            left: 40
        };
        var width = 560 - margin.left - margin.right;
        var height = 200 - margin.top - margin.bottom;
        var svg = d3.select(obj).append('svg').attr('width', width + margin.left + margin.right).attr('height', height + margin.top + margin.bottom).attr('class', '').append('g').attr('transform', "translate(" + margin.left + ", " + margin.top + ")"); //   const yMin = d3.min(jsonData, function(d){ return +d3.min(Object.values(d))});

        var yMin = 0;
        // var yMax = lineGraphYMax;
        var xScale = d3.scaleLinear().domain([0, 300]).range([0, width]); // TODO make dynamic
        var yScale = d3.scaleLinear().domain([0, lineGraphYMax]).range([height, 0]);

        var xAxis = svg.append('g').attr('transform', "translate(0, " + height + ")").call(d3.axisBottom(xScale));
        xAxis.select('.domain').attr('opacity', '0');
        xAxis.selectAll('text').attr('opacity', '0');
        xAxis.append('text').attr('class', 'axis-title').attr('transform', "translate(" + width + ")").attr('y', -6).text('Time (s)');
        var yAxis = svg.append('g').call(d3.axisLeft(yScale).ticks(5));
        var yAxis2 = svg.append('g').call(d3.axisLeft(yScale).ticks(5)).attr('transform', "translate(460 0)");
        var yAxis3 = svg.append('g').call(d3.axisLeft(yScale).ticks(5)).attr('transform', "translate(495 0)");
        yAxis.select('.domain').attr('opacity', '0');
        yAxis.selectAll('line').attr('opacity', '0');
        yAxis2.select('.domain').attr('opacity', '0');
        yAxis2.selectAll('line').attr('opacity', '0');
        yAxis3.select('.domain').attr('opacity', '0');
        yAxis3.selectAll('line').attr('opacity', '0');

        var colors = d3.scaleOrdinal(_.range(dataKeys.length), d3.schemeCategory10);
        var clip = svg.append('defs').append('svg:clipPath').attr('id', "clip").append('svg:rect').attr('width', width).attr('height', height).attr('x', 0).attr('y', 0);
        var brush = d3.brushX().extent([[0, 0], [width, height]]).on('end', updateChart);
        drawLines();
        svg.append('g').attr('class', 'brush').call(brush);
        svg.select('.overlay').on('mousemove', mouseMove).on('mouseout', mouseOut);

        function mouseMove() {
            svg.selectAll('.focus').attr('display', null);
            var tData = mapData.map(function (d) {
                return d.time;
            });
            var x0 = xScale.invert(d3.mouse(this)[0]);
            var i = d3.bisectLeft(tData, x0, 0);
            var d0 = i - 1;
            var d1 = i;
            var d = x0 - d0 > d1 - x0 ? d1 : d0;
            var dataPoint = mapData[d];

            for (var _iterator = dataKeys, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator]();;) {
                var _ref;

                if (_isArray) {
                    if (_i >= _iterator.length) break;
                    _ref = _iterator[_i++];
                } else {
                    _i = _iterator.next();
                    if (_i.done) break;
                    _ref = _i.value;
                }

                var key = _ref;
                svg.select("#" + key + "-tip").attr('transform', "translate(" + xScale(dataPoint.time) + " , " + yScale(dataPoint.d[key]) + ")").select('text').text(dataPoint.d[key]);
            }


            var minY = d3.min(Object.values(jsonData[d]).map(function (d) {
                return yScale(d);
            }));
            svg.select('.focus.line').attr('transform', "translate(" + xScale(d) + ")").attr('y1', minY);
        }

        function mouseOut() {
            svg.selectAll('.focus').attr('display', 'none');
        }

        function drawLines() {
            svg.append('g').attr('clip-path', "url(#clip)").attr('fill', 'none').attr('stroke-width', 1).selectAll('path').data(datums).join("path").attr('class', "line").attr('stroke', function (d, i) {
                return colors(i);
            }).attr('d', d3.line().x(function (d) {
                return xScale(d.time);
            }).y(function (d) {
                return yScale(d.value);
            }));
            drawTips();
            drawLegend();

        }



        function drawTips() {
            svg.append('line').attr('class', 'focus line').attr('y1', 0).attr('y2', height);

            for (var _i2 = 0, _dataKeys = dataKeys; _i2 < _dataKeys.length; _i2++) {
                var key = _dataKeys[_i2];
                var tooltip = svg.append('g').attr('id', key + "-tip").attr('class', 'focus tip').attr('display', 'none');
                tooltip.append('rect').attr('fill', '#fff').attr('x', '3').attr('y', '-5').attr('width', 22).attr('height', '1em').attr('opacity', .8);
                tooltip.append('circle').attr('r', 3).attr('fill', 'transparent').attr('stroke', 'red');
                tooltip.append('text').attr('class', 'text').attr('x', 9).attr('dy', '.35em');
            }
        }

        function drawLegend() {
            var legend = d3.select('#chart-svg').append('div').attr('class', 'legend');
            var i = 0;

            for (var _i3 = 0, _dataKeys2 = dataKeys; _i3 < _dataKeys2.length; _i3++) {
                var key = _dataKeys2[_i3];
                series = legend.append('div');
                series.append('div').attr('class', 'series-marker').style('background-color', colors(i));
                series.append('p').text(key);
                i += 1;
            }
        }

        function updateLines() {
            svg.selectAll('path').data(datums).enter();
        }

        function updateChart() {
            var extent = d3.event.selection;

            if (!extent) {
                if (!idleTimeout) return idleTimeout = setTimeout(idled, 350);
                xScale.domain([0, 300]);
            } else {
                xScale.domain([xScale.invert(extent[0]), xScale.invert(extent[1])]);
                svg.select('.brush').call(brush.move, null);
            }

            //xAxis.transition().duration(1000).call(d3.axisBottom(xScale));
            svg.selectAll('.line').data(datums).transition().duration(1000).attr('d', d3.line().x(function (d) {
                return xScale(d.time);
            }).y(function (d) {
                return yScale(d.value);
            }));
            return true;
        }

        function idled() {
            idleTimeout = null;
        }
    }

};


//위치별 고장 발생
var subwayLine = {
    init: function(){

        var svg = d3.select("#subway")
            .attr("width", 1119)
            .attr("height", 150);
        var g = svg.append("svg").attr("class","group");
        g.attr('x',0);
        g.attr('y',120);
        g.append('rect')
            .attr('fill', '#d3d6e0')
            .attr('x',5)
            .attr('y',4)
            .attr('height',8)
            .attr('width',1133);
        var gap = 0;

        var _data = data_station.pointData;
        for(var i=0; i<= _data.length-1; i++){
            var point = _data[i].point;
            var color = _data[i].color;
            var label = _data[i].label;
            if(point){
                gap += 2;
                $('#subway-wrap').append('<span class="label" id="label'+i+'">'+label+'</span>');
                $('#label'+i).css('left',gap);
                // svg.append('text')
                // .attr('fill', '#333')
                // .attr("dx", gap + 8)
                // .attr("dy", "80px")
                // .style("height", "20px")
                // .attr("class", "label")
                // .attr("id", "label"+i)
                // .attr("letter-spacing", "-3")
                // .attr("viewBox", "0 0 50 50")
                // .style("writing-mode", "tb")
                // .style("text-anchor", "top")
                // .style("font-size", "12px")
                // .style("writing-mode", "vertical-rl")
                // .style("inline-size", "50px")
                // .style("white-space", "pre-line")
                //.text(label);

                g.append('circle')
                    .attr('fill', color)
                    .attr("r", 6)
                    .attr('stroke', '#d3d6e0')
                    .attr('stroke-width', '2')
                    .attr("cy", 8)
                    .attr("cx", 8 + gap);
                gap += 12;
            }else{
                g.append('circle')
                    .attr('fill', color)
                    .attr("r", 3)
                    .attr('stroke', '#d3d6e0')
                    .attr('stroke-width', '2')
                    .attr("cy", 8)
                    .attr("cx", 8 + gap);
                gap += 10;
            }
        }
    }
};


