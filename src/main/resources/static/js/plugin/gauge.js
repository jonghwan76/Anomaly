var notch = echarts.init(document.getElementById('notch')); //Notch
var xAxisData = [];
// var data1 = [0];
// var data2 = [0];

var itemStyle = {
    normal: {
        label: {
            show: false,
            position:'right',
            width:'60',
            fontFamily:'NanumSquareRound',
            fontWeight: '800',
            color:'#444',
            fontSize:'12',
            borderRadius: 11,
            borderWidth: 1,
            borderColor: '#1e3f8c',
            padding:[5, 15]
        }
    },
    emphasis: {
        label: {
            position: 'outside'
        },
        barBorderColor: '#fff',
        barBorderWidth: 1,
        shadowBlur: 10,
        shadowOffsetX: 0,
        shadowOffsetY: 0,
        shadowColor: 'rgba(0,0,0,0.5)'
    }
};

var notchOption = {
    tooltip: {
      textStyle: {fontSize:12, color:'#fff'}
    },
    grid: {
        top:13,
        left:-32
    },
    xAxis: {
        show:false,
        data: xAxisData,
        name: 'Notch',
        type: 'category',
        data: []
    },
    yAxis: {
        show:true,
        max:100,
        min:-100,
        axisLabel: {
          formatter: '{value} %'
        },
        splitLine: {//y 좌표 선
             show: false
        }
    },
    series: [{
        name: '추진',
        type: 'bar',
        stack: 'one',
        barWidth: '16%',
        color:'#0100FF',
        itemStyle: itemStyle,
        data: [0]
    },
    {
        name: '제어',
        type: 'bar',
        stack: 'one',
        barWidth: '16%',
        color:'#FF0000',
        itemStyle: itemStyle,
        data: [0]
      }]
};

//notch.setOption(notchOption);

//Speed(Km/h)
var speedData1 = [0];   //목표속도
var speedData2 = [0];   //현재속도

var speed1 = Gauge(document.getElementById("speed1"), {
    name: '목표속도',
    max: 120,
    min: 0,
    value: speedData1,
    valueDialClass: "value",
    valueClass: "value-text",
    dialClass: "dial",
    gaugeClass: "gauge",
    showValue: false,
    gaugeColor: null,
});

var speed2 = Gauge(document.getElementById("speed2"), {
    name: '현재속도',
    max: 120,
    min: 0,
    value: speedData2,
    valueDialClass: "value",
    valueClass: "value-text",
    dialClass: "dial",
    gaugeClass: "gauge",
    showValue: false,
    gaugeColor: null,
});
var speed = echarts.init(document.getElementById('speed'));
var speedOption = {
    animation: true,
    series : [
        {
            name:'Target',
            type:'gauge',
            radius : '78%',
            min:0,
            max:120,
            splitNumber:10,
            axisLine: {
                lineStyle: {color: [[1, 'transparent']], width:10}
            },
            axisTick: {show:false, splitNumber:10, length :15},
            axisLabel: {show:false},
            splitLine: {show:false},
            pointer: {show:false},
            title : {
                show: true,
                textStyle: {
                    fontFamily:'NanumSquareRound',
                    fontWeight: '800',
                    fontSize: 12,
                    color:'#444'
                },
                offsetCenter: ['-6%', '50%']
            },
            detail: {
                  fontSize:14,
                  fontWeight: '700',
                  lineHeight: 13,
                  textStyle: {
                  color: '#444',
                  padding: [4,10]
              },
              offsetCenter: ['0', '80%'],
              borderRadius: 11,
              borderWidth: 1,
              borderColor: '#54bf14',
              formatter: '{value}'
          },
              data:[{value: speedData1, name: 'Target'}]
          },
          {
              name:'Current',
              type:'gauge',
              radius : '92%',
              min:0,
              max:120,
              splitNumber:10,
              axisLine: {
                  lineStyle: {color: [[1, 'transparent']], width:10}
              },
              axisLabel: {fontSize:'10', color:'#444'},
              axisTick: {show: false},
              splitLine: {show:false},
              pointer: {show:false},
              title : {
                  show: true,
                  textStyle: {
                  fontFamily:'NanumSquareRound',
                  fontWeight: '800',
                  fontSize: 12,
                  color:'#444'
              },
              offsetCenter: ['0', '-20%']
          },
              detail: {
                  width:60,
                  fontSize:14,
                  lineHeight: 13,
                  fontWeight: '700',
                  textStyle: {
                      color: '#444',
                      padding: [4,15]
                  },
                  offsetCenter: ['0', '5%'],
                  borderRadius: 11,
                  borderWidth: 1,
                  borderColor: '#1e3f8c',
                  formatter: '{value}'
              },
              data:[{value: speedData2, name: 'Current'}]
          }
      ]
  };

speed.setOption(speedOption);

//Line Voltage(kV)
var lineVoltageData = [0];

var lineVoltage1 = Gauge(document.getElementById("lineVoltage1"), {
    name: 'Line Voltage',
    max: 60,
    min: 0,
    value: lineVoltageData,
    valueDialClass: "value",
    valueClass: "value-text",
    dialClass: "dial",
    gaugeClass: "gauge",
    showValue: false,
    gaugeColor: null,
});

var lineVoltage = echarts.init(document.getElementById('lineVoltage'));

var lineVoltageOption = {
        tooltip: {
          formatter: '{c}kV'
    },
    series : [
        {
            name:'Line Voltage(kV)',
            type:'gauge',
            radius: '75%',
            clockwise: true,
            min:0,
            max:60,
            splitNumber:6,
            axisLine: {
                show:false,
                lineStyle: {
                    color: [[1, '#2e6cca']],
                    width: 10
                }
            },
            axisTick: {show:false},
            splitLine: {
                length :15,
                lineStyle: {
                    color: '#444'
                }
            },
            axisLabel: {fontSize:'12', color:'#222'},
            title : {
                textStyle: {
                    fontFamily:'NanumSquareRound',
                    fontWeight: '800',
                    fontSize: 16,
                    color:'transparent'
                },
                offsetCenter: [0, '-120%']
            },
            pointer: {
                show:true,
                length:'70%',
                width:3
            },
            detail: {
                fontSize:14,
                lineHeight: 13,
                fontWeight: '700',
                textStyle: {
                    color: '#444',
                    padding: [4, 20]
                },
                offsetCenter: [0, '100%'],
                borderRadius: 11,
                borderWidth: 1,
                borderColor: '#1e3f8c',
                formatter: '{value}'
            },
            data: [{value: lineVoltageData}]
        }
    ]
};
lineVoltage.setOption(lineVoltageOption);

//Motor Current(A)
var motorCurrentData = [0];

var motorCurrent1 = Gauge(document.getElementById("motorCurrent1"), {
    name: 'Motor Current',
    max: 3,
    min: 0,
    value: motorCurrentData,
    valueDialClass: "value",
    valueClass: "value-text",
    dialClass: "dial",
    gaugeClass: "gauge",
    showValue: false,
    gaugeColor: null,
});

var motorCurrent = echarts.init(document.getElementById('motorCurrent'));

var motorCurrentOption = {
    tooltip: {
        formatter: '{c}A'
    },
    series : [
        {
            name:'Motor Current(A)',
            type:'gauge',
            radius: '75%',
            clockwise: true,
            min:0,
            max:3,
            splitNumber:5,
            axisLine: {
                show:false,
                lineStyle: {
                    color: [[1, '#efba2a']],
                    width: 10
                }
            },
            axisTick: {show:false},
            axisLabel: {fontSize:'12', color:'#222'},
            splitLine: {
                length :15,
                lineStyle: {
                    color: '#444'
                }
            },
            pointer: {
                show:true,
                length:'70%',
                width:3
            },
            detail: {
                fontSize:14,
                lineHeight: 13,
                fontWeight: '700',
                textStyle: {
                    color: '#444',
                    padding: [4,15]
                },
                offsetCenter: [0, '100%'],
                borderRadius: 11,
                borderWidth: 1,
                borderColor: '#1e3f8c',
                formatter: '{value}'
            },
            data: [{value: motorCurrentData}]
      }
    ]
};
motorCurrent.setOption(motorCurrentOption, true);

//Battery(A)
var batteryData = [0];

var battery1 = Gauge(document.getElementById("battery1"), {
    name: 'Battery',
    max: 60,
    min: 0,
    value: batteryData,
    valueDialClass: "value",
    valueClass: "value-text",
    dialClass: "dial",
    gaugeClass: "gauge",
    showValue: false,
    gaugeColor: null,
});

var battery = echarts.init(document.getElementById('battery'));

var batteryOption = {
    tooltip: {
        formatter: '{c}A'
    },
    series : [
        {
            name:'Battery(V)',
            type:'gauge',
            radius: '75%',
            clockwise: true,
            min:0,
            max:60,
            splitNumber:3,
            axisLine: {
                show:false,
                lineStyle: {
                    color: [[1, '#5bc6e9']],
                    width: 10
                }
            },
            axisTick: {show:false},
            axisLabel: {fontSize:'12', color:'#222'},
            splitLine: {
                length :15,
                lineStyle: {
                    color: '#444'
                }
            },
            pointer: {
                show:true,
                length:'70%',
                width:3
            },
            detail: {
                fontSize:14,
                lineHeight: 13,
                fontWeight: '700',
                textStyle: {
                    color: '#444',
                    padding: [4,15]
                },
                offsetCenter: [0, '100%'],
                borderRadius: 11,
                borderWidth: 1,
                borderColor: '#1e3f8c',
                formatter: '{value}'
            },
            data: [{value: batteryData}]
      }
    ]
};

battery.setOption(batteryOption);

//Pressure(kg/cm2)
var pressure = echarts.init(document.getElementById('pressure'));

var pressureOption = {
        tooltip : {
          formatter: '{b} {c}'
        },
        series : [
        {
          name:'BC',
          type:'gauge',
          radius : '75%',
          min:0,
          max:800,
          splitNumber:5,
          axisLine: {
              lineStyle: {
                  color: [[0.3, '#228b22'],[0.7, '#48b'],[1, '#ff4500']],
                  width: 10
              }
          },
          axisTick: {
              splitNumber:5,
              length :7,
              lineStyle: {
                  color: 'auto'
              }
          },
          splitLine: {
              length :15,
              lineStyle: {
                  color: 'auto'
              }
          },
          pointer: {
              show:true,
              length:'60%',
              width:3
          },
          title : {
              show: true,
              textStyle: {
              fontFamily:'NanumSquareRound',
              fontWeight: '800',
              fontSize: 12,
              color:'#444'
              },
              offsetCenter: ['-110%', '102%']
            },
            detail: {
                fontSize:14,
                lineHeight: 13,
                fontWeight: '700',
                textStyle: {
                    color: '#444',
                    padding: [4,15]
                },
                offsetCenter: ['-55%', '100%'],
                borderRadius: 11,
                borderWidth: 1,
                borderColor: '#1e3f8c',
                formatter: '{value}'
          },
              data:[{value: pressureBC, name: 'BC'}]
          },
            {
                name:'MR',
                type:'gauge',
                radius : '75%',
                min:0,
                max:800,
                splitNumber:5,
                axisLine: {
                    lineStyle: {
                        color: [[0.4, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                        width: 10
                    }
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    length :15,
                    lineStyle: {
                        color: 'auto'
                    }
                },
                pointer: {
                    show:true,
                    length:'60%',
                    width:3
                },
                title : {
                    show: true,
                    textStyle: {
                        fontFamily:'NanumSquareRound',
                        fontWeight: '800',
                        fontSize: 12,
                        color:'#444'
                    },
                    offsetCenter: ['10%', '102%']
                },
                detail: {
                    width:60,
                    fontSize:14,
                    lineHeight: 13,
                    fontWeight: '700',
                    textStyle: {
                        color: '#444',
                        padding: [4,15]
                    },
                    offsetCenter: ['60%', '100%'],
                    borderRadius: 11,
                    borderWidth: 1,
                    borderColor: '#e79700',
                    formatter: '{value}'
                },
                data:[{value: pressureAS, name: 'AS'}]
            }
      ]
  };

pressure.setOption(pressureOption);

// var speedData1 = 0;
// var speedData2 = 0;
var lineVoltageData = 0;
var motorCurrentData = 0;
var batteryData = 0;

setInterval(function () {
    //speedData1 = Math.round(Math.random() * 100);
    //speedData2 = Math.round(Math.random() * 100);

    // console.log("현재/목표속도:" + speedData1 + "," + speedData2);
    speedOption.series[0].data[0].value = speedData1;
    speedOption.series[1].data[0].value = speedData2;
    speed.setOption(speedOption, true);
    speed1.setValueAnimated(speedData1);
    speed2.setValueAnimated(speedData2);

    // lineVoltageData = Math.round(Math.random() * 30);
    lineVoltageOption.series[0].data[0].value = lineVoltageData;
    lineVoltage.setOption(lineVoltageOption, true);
    lineVoltage1.setValueAnimated(lineVoltageData);

    // motorCurrentData = Math.round(Math.random() * 5);
    motorCurrentOption.series[0].data[0].value = motorCurrentData;
    motorCurrent.setOption(motorCurrentOption, true);
    motorCurrent1.setValueAnimated(motorCurrentData);

    // batteryData = Math.round(Math.random() * 150);
    batteryOption.series[0].data[0].value = batteryData;
    battery.setOption(batteryOption, true);
    battery1.setValueAnimated(batteryData);

    pressureOption.series[0].data[0].value = pressureBC;
    pressureOption.series[1].data[0].value = pressureAS;
    // pressureOption.series[0].data[0].value = (Math.random()*12).toFixed(0) - 0;
    // pressureOption.series[1].data[0].value = (Math.random()*12).toFixed(0) - 0;
    pressure.setOption(pressureOption);

    notchOption.series[0].data = [push];
    notchOption.series[1].data = [-brake];
    notch.setOption(notchOption);

    // console.log("Notch:" + push + "," + brake);
    $('#push').val(push);
    $('#brake').val(brake);
},2000);
