(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 100)
(def mouse-pos (atom [0 0]))

(defn draw []
  "Evaluate to draw next frame."
  (do
    (framerate 24)
    (smooth)
    (background-float 255)
    ;; horizontal line
    (stroke 0 30)
    (stroke-weight 5)
    (line 20 50 480 50)
    ;; random lines
    (stroke 20 50 70)
    (stroke-weight 2)
    (draw-random-lines 20 10 460 460)))

(defn draw-random-lines [border-x border-y width steps]
  (loop [x border-x
         y (/ *height* 2)
         n steps
         y-noise (map-mouse-pos-to-noise)]
    (if (> n 0)
      (let [next-x (+ x (/ width steps))
            next-y (+ 10 (* 80 (noise y-noise)))]
        (line x y next-x next-y)
        (recur next-x
               next-y
               (- n 1)
               (+ y-noise (map-mouse-pos-to-noise-inc)))))))

(defn map-mouse-pos-to-noise []
  (let [[x y] @mouse-pos] (map-to x 0 *width* 0 10)))

(defn map-mouse-pos-to-noise-inc []
  (let [[x y] @mouse-pos] (map-to y 0 *height* 0 0.2)))

(defn setup []
  "Runs once."
  (smooth))

(defn mouse-moved [e]
  (reset! mouse-pos [(.getX e) (.getY e)]))

(defapplet app :title "Chapter 3"
  :setup setup :draw draw :size [*width* *height*]
  :mouse-moved mouse-moved)

(run app)

(stop app)
