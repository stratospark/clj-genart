(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(defn draw []
  "Evaluate to draw next frame."
  (background-float 100)
  (smooth)
  (line 100 100 200 200))

(defn setup []
  "Runs once."
  (smooth)
  (no-stroke)
  (framerate 12)
  (fill 255))

(defapplet app :title "Basic App"
  :setup setup :draw draw :size [400 400])

(run app)

(stop app)
