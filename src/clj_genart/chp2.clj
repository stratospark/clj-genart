(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)
(def diam (atom 10))

(defn draw []
  "Evaluate to draw next frame."
  (framerate 24)
  (smooth)
  (background-float 180)
  (let [cx (/ (width) 2)
        cy (/ (height) 2)]
    (stroke-float 0)
    (stroke-weight 5)
    (fill-float 255 50)
    (ellipse cx cy @diam @diam)
    (swap! diam + 10)
    (if (> @diam 400)
      (reset! diam 10))))

(defn setup []
  "Runs once."
  (smooth))


(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
