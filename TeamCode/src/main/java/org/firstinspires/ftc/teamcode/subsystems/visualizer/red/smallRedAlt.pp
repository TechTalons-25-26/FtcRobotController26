{
  "startPoint": {
    "x": 88,
    "y": 8,
    "heading": "linear",
    "startDeg": 90,
    "endDeg": 180
  },
  "lines": [
    {
      "name": "smallRedStart_smallRedPreload",
      "endPoint": {
        "x": 88,
        "y": 16,
        "heading": "linear",
        "startDeg": 90,
        "endDeg": 70
      },
      "controlPoints": [],
      "color": "#8B788C",
      "id": "line-fp6fo18gn6p",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "locked": true
    },
    {
      "id": "mkhm3vte-2gbi62",
      "name": "smallRedPreload_smallRedAltEnd",
      "endPoint": {
        "x": 108,
        "y": 12,
        "heading": "linear",
        "reverse": false,
        "startDeg": 70,
        "endDeg": -11
      },
      "controlPoints": [],
      "color": "#9B9565",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "locked": false
    }
  ],
  "shapes": [],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-fp6fo18gn6p"
    },
    {
      "kind": "path",
      "lineId": "mkhm3vte-2gbi62"
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
  "timestamp": "2026-01-17T01:57:01.554Z"
}