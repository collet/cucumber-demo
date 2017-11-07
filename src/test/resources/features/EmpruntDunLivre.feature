Feature: Emprunter un livre

  Background:
    Given un etudiant de nom "Marcel" et de noEtudiant 123456
    And un livre de titre "UML pour les nuls"

  Scenario: Pas d'emprunt par défaut
    When "Marcel" demande son nombre d'emprunt
    Then Il y a 0 dans son nombre d'emprunts

  Scenario: emprunt d'un livre
    When "Marcel" emprunte le livre "UML pour les nuls"
    Then Il y a 1 dans son nombre d'emprunts
      And Il y a le livre "UML pour les nuls" dans un emprunt de la liste d'emprunts
      And Le livre "UML pour les nuls" est indisponible

