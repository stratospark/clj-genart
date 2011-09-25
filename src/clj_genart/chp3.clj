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
  ;; horizontal line
  (stroke 0 30)
  (stroke-weight 5)
  (line 20 50 480 50)
  ;; random lines
  (stroke 20 50 70)
  (draw-random-lines 20 10 460 50))

(defn draw-random-lines [border-x border-y width steps]
  (loop [x border-x
         y (/ *height* 2)
         n steps]
    (if (> n 0)
      (let [next-x (+ x (/ width steps))
            next-y (+ y (- (rand-int 20) 10))]
        (line x y next-x next-y)
        (recur next-x
               next-y
               (- n 1))))))


(defn setup []
  "Runs once."
  (smooth))

(defapplet app :title "Chapter 3"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
