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
    (stroke-weight 1)
    (fill-float 255 50)
    (draw-ellipses cx cy @diam 10 10)
    (swap! diam + 10)
    (if (> @diam 400)
      (reset! diam 10))))

(defn draw-ellipses [cx cy cur-diam end-diam step]
  (if (> cur-diam end-diam)
    (do
      (ellipse cx cy cur-diam cur-diam)
      (recur cx cy (- cur-diam step) end-diam step))))

(defn setup []
  "Runs once."
  (smooth))


(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
