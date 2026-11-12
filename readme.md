# Refactoring SOLID d'une application de facturation (SRP, OCP, LSP)

Une application de facturation contenait trois défauts de conception. Chacun a été corrigé séparément, avec des tests exécutés après chaque étape et un comportement comparé avant et après.

## Structure du projet

```
TP3 POO/
├── .idea/                          généré par IntelliJ (ignoré par Git)
├── out/                            classes compilées (ignoré par Git)
├── src/                            code source (Sources Root)
│   ├── discount/                   Parties C et D (OCP)
│   │   ├── DiscountService.java    reçoit une DiscountStrategy
│   │   ├── DiscountStrategy.java   interface : double apply(double amount)
│   │   ├── SeniorDiscount.java     -15 % (ajoutée par extension)
│   │   ├── StandardDiscount.java   -5 %
│   │   ├── StudentDiscount.java    -10 %
│   │   └── VipDiscount.java        -20 %
│   ├── invoice/                    Parties A et B (SRP)
│   │   ├── Invoice.java            données : la liste des montants
│   │   ├── InvoiceCalculator.java  calcul du total
│   │   ├── InvoicePrinter.java     affichage
│   │   └── InvoiceRepository.java  persistance
│   ├── shape/                      Parties E et F (LSP)
│   │   ├── Rectangle.java          immuable, dimensions validées
│   │   ├── Shape.java              interface : int area()
│   │   └── Square.java             immuable, côté validé
│   └── Main.java                   scénarios de comparaison avant/après (package par défaut)
├── .gitignore                      out/, .idea/, *.iml
├── README.md
└── TP3 POO.iml                     généré par IntelliJ (ignoré par Git)
```

Dépendances : `InvoicePrinter` utilise `InvoiceCalculator` ; `InvoiceCalculator`, `InvoicePrinter` et `InvoiceRepository` utilisent `Invoice`, qui ne dépend d'aucun d'eux. `DiscountService` dépend de `DiscountStrategy`, que les trois remises implémentent. `Rectangle` et `Square` implémentent `Shape` et ne dépendent pas l'un de l'autre.

## Diagnostic et priorisation

| Symptôme | Principe probable | Priorité | Correction |
|---|---|---|---|
| Classe difficile à décrire en une phrase | SRP | Moyenne | Séparer les responsabilités en plusieurs classes |
| `if/else` modifié à chaque nouveau type | OCP | Haute | Remplacer par le pattern Strategy |
| Sous-classe modifiant une garantie de la classe mère | LSP | Haute | Supprimer l'héritage fautif, passer par une abstraction commune |
| Code stable rarement modifié | Aucun principe urgent | Basse | Ne pas refactoriser maintenant, le couvrir par des tests |
| Zone métier fréquemment étendue | OCP | Haute | Introduire une abstraction pour étendre par ajout de classes |

Justification : on traite en priorité les violations situées dans les zones fréquemment modifiées, car c'est là que chaque modification refait courir un risque de régression. Refactoriser un code stable apporte peu de bénéfice et ajoute un risque.

## Violations, principes, corrections et bénéfices

| Violation trouvée | Principe | Correction | Bénéfice |
|---|---|---|---|
| `Invoice` calculait le total, s'affichait et s'enregistrait : trois raisons de changer | SRP | `Invoice` ne garde que ses données. Le calcul passe dans `InvoiceCalculator`, l'affichage dans `InvoicePrinter`, l'enregistrement dans `InvoiceRepository` | Un changement de TVA, d'affichage ou de stockage ne modifie qu'une seule classe |
| `DiscountService` : un `if/else` à modifier pour chaque nouveau type de remise | OCP | Interface `DiscountStrategy`, une classe par remise, et `DiscountService` qui reçoit la stratégie | Un nouveau type de remise s'ajoute par une nouvelle classe, sans modifier l'existant |
| `Square extends Rectangle` : `setWidth` modifiait aussi la hauteur, donc 16 au lieu de 20 pour un appelant qui manipule un `Rectangle` | LSP | Interface `Shape`, avec `Rectangle` et `Square` immuables et indépendants, dimensions validées | Un code client qui utilise `Shape` obtient un comportement fiable pour toute forme |

## Comparaison avant/après

Les tests de l'étape 0 décrivent le comportement de l'ancien code. Les mêmes valeurs attendues sont conservées après chaque correction :

- Facture : total de 78,30 €, même texte d'affichage, même message d'enregistrement.
- Remises : 95 (STANDARD), 80 (VIP), 90 (STUDENT) pour un montant de 100, et aucune remise pour un type inconnu.
- Formes : un rectangle 5×4 donne 20, un carré de côté 5 donne 25.

Le seul scénario qui ne peut plus exister est celui de la violation LSP : un `Square` utilisé comme `Rectangle` ne peut plus donner 16 au lieu de 20.

## Changements de comportement voulus

- `Square` n'est plus un `Rectangle` : le scénario `setWidth` puis `setHeight` n'existe plus.
- Les dimensions inférieures ou égales à 0 sont refusées (`IllegalArgumentException`). L'ancien code les acceptait.
- Un dépassement de capacité d'un `int` lève une `ArithmeticException` au lieu de renvoyer un résultat faux.
- `DiscountService` ne reçoit plus un type en texte : l'appelant choisit la stratégie. Pour ne donner aucune remise, on utilise `amount -> amount`.

## Synthèse des choix techniques

- Un package par principe corrigé (`invoice`, `discount`, `shape`), pour que chaque correction reste isolée.
- Le pattern Strategy remplace le `if/else`, car chaque nouvelle remise devient une classe sans toucher à `DiscountService`.
- Les formes sont immuables et validées à la construction, ce qui supprime les effets de bord à l'origine de la violation LSP.
- Pas de sur-ingénierie : pas d'interface pour `InvoicePrinter` ni `InvoiceRepository` (une seule implémentation pour l'instant), pas de registre de stratégies.
- Les montants restent en `double` pour rester proches de l'énoncé. Pour de l'argent en production, `BigDecimal` serait préférable.
- Les tests n'utilisent aucune dépendance externe : `Check` et `run_tests.sh` suffisent.

## Exécuter les tests


Le script compile `src/` et `src/test`, puis exécute `InvoiceTest`, `DiscountTest` et `ShapeTest`. Java 17 ou plus est conseillé (le projet utilise un `record`). Si le script n'est pas exécutable, lancer `bash run_tests.sh`.

## Historique Git

1. `chore` : code initial avec les trois violations, et tests décrivant son comportement
2. `refactor(invoice)` : correction SRP
3. `refactor(discount)` : correction OCP
4. `refactor(shape)` : correction LSP
5. `docs` : ce README
