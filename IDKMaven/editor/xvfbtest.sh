env DISPLAY=:1 Xvfb :1 -screen 0 1280x1024x24 &
sleep 2
env DISPLAY=:1 fluxbox &
sleep 2
env DISPLAY=:1 x11vnc -display :1 -bg -nopw -listen localhost -xkb&
sleep 2
vncviewer  -encodings 'copyrect tight zrle hextile' localhost:5900 &
env DISPLAY=:1 mvn test
killall -9 vncviewer
killall -9 Xvfb


