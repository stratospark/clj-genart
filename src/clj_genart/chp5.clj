(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 300)
(def *height* 300)

(defn custom-noise [value]
  (let [count (mod value 12)]
    (pow (sin value) count)))

(defn draw []
  "Evaluate to draw next frame."
  (frame-rate 1)
  (background-float 255)
  (draw-grid)
)

(defn draw-grid-row [y x-noise y-noise]
  (loop [x 0
         x-noise x-noise
         y-noise y-noise
         ]
    (stroke-int 0 (* 255 (noise x-noise y-noise)))
    (line x y (inc x) (inc y))
    (if (< x *width*)
      (recur (inc x)
             (+ x-noise 0.01)
             (+ y-noise 0)))))

(defn draw-grid []
  (loop [y *height*
         x-noise (rand-int 10)
         y-noise (rand-int 10)]
    (draw-grid-row y x-noise y-noise)
    (if (> y 0)
      (recur (dec y)
             x-noise
             (+ y-noise 0.03)))))



(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 5"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
