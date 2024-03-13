# PROJECT of Oriented Object's Design

This upload corresponds with the Project of OOD available [here](media/competitions.pdf "Project").
This project consisted in creating a design of competition.

---
##  Squad's Members

* Nollet Antoine
* Minaud Mathilde

---
## UML Design

Let's find here the **UML** diagram corresponding to our design for the competition project :

<br />
<div style="text-align:center"><img src="media/pictures/UML.png"alt="UML"/></div>
<br />

---
## Content of this work

* **dist** (distribution of executables `.jar`)
* **lib** (library containing what we need to do the tests and to create the executables)
* **src** (contains the differents codes `.java`)
* **test** (contains `test` programs)
* **media** (contains the `sounds` and `pictures`)
* **makefile** (file making easier this work's using)
* [**readme.md**](readme.md "readme menu") (the readme's menu)
* [**[fr]readme.md**]([fr]readme.md "readme in french") (the file you are reading, in french)
* [**[en]readme.md**]([en]readme.md "readme in english") (the file you are reading, in english)
* [**[es]readme.md**]([es]readme.md "readme in spanish") (the file you are reading, in spanish)

---
## Explanations

If you're reading that, you've been granted to access to our OOD projects.
Use the clone option of this OOD folder and create a local folder, if it's not already done.

Open a shell from this local folder, the **git pull** command is recommended for each times you come back to this work.

From the root of this work folder, in a shell, you could use these differents commands :

* **make** : run the `executable` of this project
* **make comp** : build the differents `classes`
* **make compTest** : build the `testing classes`
* **make doc** : create the `documentation`
* **make test** : make the differents `tests`
* **make extract** : extract the classes from the `.jar` files
* **make exe** : create the `executables`
* **make competition args="c n"** : run the `executable` of this project
* **make clean** : delete all the `.class` files, the `docs` folder and the extracted files created with `make extract`

---
## 'make competition' : Use message

    run with make competition args = "c n"
    where:
    c       = the type of Competition you use (here "t" for Tournament or "l" for League)
    n       = the number of Competitors who participate in the Competition (a power of 2 if you selected the Tournament)
    try:
    make competition args="l 3"
    or
    make competition args="t 4"

---
## Update Master

Find [here](media/competitionsV2.pdf "Update Projet") the new version of the subject.

---
## 'make competition' : update message

    run with make competition args = "c n g m"
    where:
    c            = the type of Competition you use (here "t" for Tournament or "l" for League)
    n            = the number of Competitors who participate in the Competition (a power of 2 if you selected the Tournament)
    g [if c = m] = the number of groups (for Master)
    m [if c = m] = the number of members per groups (for Master)
    warning for using Master:
    m = g*m and there at least one valid filter for this numbers n g and m
    try:
    make competition args="l 3"
    or
    make competition args="t 4"
    or
    make competition args="m 8 2 4"
---

If you have questions about this project, don't hesitate to contact us from these emails :

* **mathilde.minaud.etu@univ-lille.fr**
* **antoine.nollet.etu@univ-lille.fr**

Thanks for your reading !
---
<br />
<br />
<br />
<div style="text-align:center"><img src="media/pictures/logo.png"alt="Université de Lille"/></div>
