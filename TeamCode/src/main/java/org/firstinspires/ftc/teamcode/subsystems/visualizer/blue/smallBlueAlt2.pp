{
  "startPoint": {
    "x": 56,
    "y": 8,
    "heading": "linear",
    "startDeg": 90,
    "endDeg": 180,
    "locked": true
  },
  "lines": [
    {
      "name": "smallBlueStart_bluePreload",
      "endPoint": {
        "x": 60,
        "y": 84,
        "heading": "tangential",
        "startDeg": 90,
        "endDeg": 130
      },
      "controlPoints": [
        {
          "x": 55.9,
          "y": 23.3
        },
        {
          "x": 74.9,
          "y": 65.6
        }
      ],
      "color": "#79BAA6",
      "id": "line-jqobilmpq9r",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "locked": false
    },
    {
      "id": "mkhls7m7-p0dqox",
      "name": "bluePreload_smallBlueAlt2End",
      "endPoint": {
        "x": 20,
        "y": 104,
        "heading": "tangential",
        "reverse": false,
        "startDeg": 110,
        "endDeg": -169
      },
      "controlPoints": [
        {
          "x": 50,
          "y": 96.2
        },
        {
          "x": 46,
          "y": 72.1
        }
      ],
      "color": "#98DAC6",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    }
  ],
  "shapes": [],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-jqobilmpq9r"
    },
    {
      "kind": "path",
      "lineId": "mkhls7m7-p0dqox"
    }
  ],
  "settings": {
    "xVelocity": 75,
    "yVelocity": 65,
    "aVelocity": 3.141592653589793,
    "kFriction": 0.1,
    "rWidth": 16,
    "rHeight": 16,
    "safetyMargin": 1,
    "maxVelocity": 40,
    "maxAcceleration": 30,
    "maxDeceleration": 30,
    "fieldMap": "decode.webp",
    "robotImage": "/robot.png",
    "theme": "auto",
    "showGhostPaths": false,
    "showOnionLayers": false,
    "onionLayerSpacing": 3,
    "onionColor": "#dc2626",
    "onionNextPointOnly": false
  },
  "version": "1.2.1",
  "timestamp": "2026-01-17T02:56:35.949Z"
}