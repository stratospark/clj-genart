(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 600)
(def *height* 600)

(defn custom-noise [value]
  (let [count (mod value 12)]
    (pow (sin value) count)))

(defn draw []
  "Evaluate to draw next frame."
  (frame-rate 1)
  (smooth)
  (background-float 0)
  (draw-grid)
)

(defn draw-point [x y noise-factor]
  (let [edge-size (* noise-factor 25)
        grey (+ 150 (* noise-factor 120))
        alph (+ 150 (* noise-factor 120))]
    (push-matrix)
    (translate x y)
    (rotate (* noise-factor (radians 540)))
    (no-stroke)
    (fill-int grey alph)
    (ellipse 0 0 edge-size (/ edge-size 2))
    (pop-matrix)))

(defn draw-grid-row [y x-noise y-noise]
  (loop [x 0
         x-noise x-noise
         y-noise y-noise
         ]
    ;; (stroke-float 10 28 180 (* 255 (noise x-noise y-noise)))
    ;; (line x y (inc x) (inc y))
    (draw-point x y (noise x-noise y-noise))
    (if (< x *width*)
      (recur (+ x 5)
             (+ x-noise 0.1)
             y-noise))))

(defn draw-grid []
  (loop [y *height*
         x-noise (rand-int 10)
         y-noise (rand-int 10)]
    (draw-grid-row y x-noise y-noise)
    (if (> y 0)
      (recur (- y 5)
             x-noise
             (+ y-noise 0.1)))))



(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 5"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
