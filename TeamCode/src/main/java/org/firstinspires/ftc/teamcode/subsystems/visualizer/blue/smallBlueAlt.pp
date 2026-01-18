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
      "name": "smallBlueStart_smallBluePreload",
      "endPoint": {
        "x": 56,
        "y": 16,
        "heading": "linear",
        "startDeg": 90,
        "endDeg": 110
      },
      "controlPoints": [],
      "color": "#79BAA6",
      "id": "line-jqobilmpq9r",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "locked": true
    },
    {
      "id": "mkhls7m7-p0dqox",
      "name": "smallBluePreload_smallBlueAltEnd",
      "endPoint": {
        "x": 36,
        "y": 12,
        "heading": "linear",
        "reverse": false,
        "startDeg": 110,
        "endDeg": -169
      },
      "controlPoints": [],
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
  "timestamp": "2026-01-17T01:57:16.620Z"
}