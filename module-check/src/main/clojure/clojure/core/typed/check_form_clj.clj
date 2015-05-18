(ns ^:skip-wiki clojure.core.typed.check-form-clj
  (:require [clojure.core.typed.check-form-common :as chk-form]
            [clojure.core.typed.analyze-clj :as ana-clj]
            [clojure.core.typed.check :as chk-clj]
            [clojure.core.typed.collect-phase :as collect-clj]
            [clojure.tools.analyzer.passes.jvm.emit-form :as emit-form]
            [clojure.core.typed.current-impl :as impl]))

(defn config-map []
  {:impl impl/clojure
   :ast-for-form ana-clj/ast-for-form
   :unparse-ns *ns*
   :collect-expr collect-clj/collect-ast
   :check-expr chk-clj/check-expr
   :eval-out-ast (partial ana-clj/eval-ast {})
   :emit-form emit-form/emit-form})

(defn check-form-info
  [form & opt]
  (apply chk-form/check-form-info (config-map)
         form opt))

(defn check-form*
  [form expected type-provided?]
  (chk-form/check-form* (config-map)
    form expected type-provided?))