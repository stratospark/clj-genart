(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 100)

(defn draw []
  "Evaluate to draw next frame."
  (framerate 12)
  (smooth)
  (background-float 255)
  (stroke 0 30)
  (stroke-weight 5)
  (line 20 50 480 50)
  (stroke 20 50 70)
  (let [rand-x (rand-int *width*)
        rand-y (rand-int *height*)]
    (line 20 50 rand-x rand-y)))

(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 3"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
