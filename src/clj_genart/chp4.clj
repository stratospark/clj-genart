(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)

(defn draw-spiral [radius cent-x cent-y]
  (stroke (rand-int 120) (rand-int 250) (rand-int 200) 80)
  (loop [radius radius
         ang (rand-int 360)
         last-x -999
         last-y -999
         radius-noise (rand-int 10)]
    (let [this-radius (+ radius (* 300 (noise radius-noise)) -100)
          rad (radians ang)
          x (+ cent-x (* this-radius (cos rad)))
          y (+ cent-y (* this-radius (sin rad)))
          ]
      (if (<= ang (+ 1440 (rand-int 1440)))
        (do
          (if (and (> last-x -999) (> last-y -999))
            (line last-x last-y x y))
          (recur (+ radius 0.5)
                 (+ ang (+ 5 (rand-int 3)))
                 x
                 y
                 (+ radius-noise 0.05)))))))

(defn custom-noise [value]
  (let [count (mod value 12)]
    (pow (sin value) count)))

(defn draw-shape [radius cent-x cent-y]
  (begin-shape)
  (fill 20 50 70 50)
  (loop [radius radius
         ang 0
         radius-noise (rand-int 10)]
    (let [rad-variance (* 30 (custom-noise radius-noise))
          this-radius (+ radius rad-variance)
          rad (radians ang)
          x (+ cent-x (* this-radius (cos rad)))
          y (+ cent-y (* this-radius (sin rad)))]
      (if (<= ang 360)
        (do
          (curve-vertex x y)
          (recur radius
                 (+ ang 1)
                 (+ radius-noise 0.6))))))
  (end-shape))

(defn draw []
  "Evaluate to draw next frame."
  (do
    (framerate 1)
    (smooth)
    (background-float 255)
    (stroke-weight 2.0)

    (let [radius 100
          cent-x 250
          cent-y 150]
      ;; Built in ellipse
      (stroke 0 30)
      (no-fill)
      (ellipse cent-x cent-y (* radius 2) (* radius 2))

      ;; Iterative spiral
      (draw-shape radius cent-x cent-y))))

(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 4"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
