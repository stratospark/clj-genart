(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)

(defn draw []
  "Evaluate to draw next frame."
  (do
    (framerate 24)
    (smooth)
    (background-float 255)
    (stroke-weight 5)

    (let [radius 100
          cent-x 250
          cent-y 150]
      ;; Built in ellipse
      (stroke 0 30)
      (no-fill)
      (ellipse cent-x cent-y (* radius 2) (* radius 2))

      ;; Iterative ellipse
      (stroke 20 50 70)
      (doseq [ang (range 0 360 5)]
        (let [rad (radians ang)
              x (+ cent-x (* radius (cos rad)))
              y (+ cent-y (* radius (sin rad)))]
          (point x y))))))


(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 4"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
