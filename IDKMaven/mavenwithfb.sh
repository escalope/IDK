env DISPLAY=:2 Xvfb :2 -screen 0 1680x1024x24 &
sleep 2
env DISPLAY=:2 fluxbox &
sleep 2
env DISPLAY=:2 x11vnc -display :2 -bg -nopw -listen localhost -xkb&
sleep 2
vncviewer  -viewonly -encodings 'copyrect tight zrle hextile' localhost:5900 &
echo $*
env DISPLAY=:2 mvn $*
killall -9 vncviewer
killall -9 Xvfb


