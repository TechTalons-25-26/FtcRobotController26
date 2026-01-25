{
  "startPoint": {
    "x": 123.2,
    "y": 123.1,
    "heading": "linear",
    "startDeg": 90,
    "endDeg": 0
  },
  "lines": [
    {
      "name": "bigBlueStart_blueShoot",
      "endPoint": {
        "x": 84,
        "y": 84,
        "heading": "tangential",
        "startDeg": 90,
        "endDeg": 180,
        "reverse": false,
        "degrees": 144
      },
      "controlPoints": [
        {
          "x": 104.4,
          "y": 109.3
        },
        {
          "x": 100,
          "y": 103.2
        }
      ],
      "color": "#7D67A8",
      "id": "line-olpcirnxzob",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "name": "blueShoot_blueEnd",
      "endPoint": {
        "x": 124,
        "y": 104,
        "heading": "tangential",
        "reverse": true
      },
      "controlPoints": [
        {
          "x": 94,
          "y": 96.2
        },
        {
          "x": 98,
          "y": 72.1
        }
      ],
      "color": "#9778CC",
      "id": "line-un6j705n4yo",
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
      "lineId": "line-olpcirnxzob"
    },
    {
      "kind": "path",
      "lineId": "line-un6j705n4yo"
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
  "timestamp": "2026-01-17T02:16:32.244Z"
}