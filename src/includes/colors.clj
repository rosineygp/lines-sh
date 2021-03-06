(def! gray          (fn* [s] (str "\033[30m" s "\033[0m")))
(def! red           (fn* [s] (str "\033[31m" s "\033[0m")))
(def! green         (fn* [s] (str "\033[32m" s "\033[0m")))
(def! yellow        (fn* [s] (str "\033[33m" s "\033[0m")))
(def! blue          (fn* [s] (str "\033[34m" s "\033[0m")))
(def! magenta       (fn* [s] (str "\033[35m" s "\033[0m")))
(def! cyan          (fn* [s] (str "\033[36m" s "\033[0m")))
(def! white         (fn* [s] (str "\033[37m" s "\033[0m")))
(def! bg-gray       (fn* [s] (str "\033[40m" s "\033[0m")))
(def! bg-red        (fn* [s] (str "\033[41m" s "\033[0m")))
(def! bg-green      (fn* [s] (str "\033[42m" s "\033[0m")))
(def! bg-yellow     (fn* [s] (str "\033[43m" s "\033[0m")))
(def! bg-blue       (fn* [s] (str "\033[44m" s "\033[0m")))
(def! bg-magenta    (fn* [s] (str "\033[45m" s "\033[0m")))
(def! bg-cyan       (fn* [s] (str "\033[46m" s "\033[0m")))
(def! bg-white      (fn* [s] (str "\033[47m" s "\033[0m")))
(def! bold          (fn* [s] (str "\033[1m"  s "\033[0m")))
(def! dark          (fn* [s] (str "\033[2m"  s "\033[0m")))
(def! underline     (fn* [s] (str "\033[4m"  s "\033[0m")))
(def! blink         (fn* [s] (str "\033[5m"  s "\033[0m")))
(def! reverse-color (fn* [s] (str "\033[7m"  s "\033[0m")))
(def! concealed     (fn* [s] (str "\033[8m"  s "\033[0m")))