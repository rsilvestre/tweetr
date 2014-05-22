<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row block-sector block-surround">
    <p>￼Projet de développement Intranet/Internet <br/>
        Charles De Groote<br/>
        3 février 2014</p>

    <h1>Projet De Développement: Tweetr</h1>

    <h2>Présentation</h2>

    <p>Le but du projet et de développer un outil de microblogage appelé « Tweetr », qui est une version
        simplifiée de l’outil<sup id="fnref:1"><a href="#fn:1" rel="footnote">1</a></sup> développé par
        l’Entreprise Twitter Inc. Le but de ce projet et de repasser en revue toute la matière et concepts vus
        durant les cours théoriques.</p>

    <p>En outre,</p>

    <ul>
        <li>Mise en place d’une application web à l’aide du language java;</li>
        <li>Utilisation d’un framework web répandu: Le modèle MVC;</li>
        <li>Mise en place d’un environnement de test (serveur et base de données locales);</li>
        <li>Utilisation des technologies « Servlets »;</li>
        <li>Utilisation de la technologie « JSP »;</li>
        <li>Utilisation de la technologie « JavaBean »;</li>
        <li>Séparation explicite des taches dans l’application;</li>
        <li>Utilisation du langage JSTL dans les vues;</li>
        <li>Utilisation de formulaires sécurisés;</li>
        <li>Utilisation de sessions;</li>
        <li>Utilisation de la notion de filtrage d’ensembles de pages, voire de l’application entière;</li>
        <li>Upload et download de fichiers;</li>
        <li>Utilisation du langage MySQL et de la technologie JDBC pour l’interaction avec la base de données;
        </li>
        <li>Utilisation du modèle DAO dans l’architecture de l’application web.</li>
    </ul>


    <h2>Fonctionnalités</h2>

    <p>Tweetr est un service de microblogage ou microblogging, qui permet à ses utilisateurs de bloguer grâce à
        de courts messages, de taille 140 caractères maximum, appelé « twit ». Outre cette concision imposée, la
        principale différence avec un blog traditionnel réside dans le fait que Tweetr n’invite pas les lecteurs
        à commenter les messages postés.</p>

    <p>Un utilisateur doit être connecté à l’application afin de pouvoir:</p>

    <ul>
        <li>ajouter/modifier les informations de son profil</li>
        <li>écrire un « twit »</li>
        <li>« retwiter » un twit. C’est-à-dire partager le « twit » de quelqu’un d’autres. • suivre des
            utilisateurs de Tweetr.
        </li>
    </ul>


    <p>De plus, un utilisateur doit pouvoir creer/modifier ou supprimer son compte.</p>

    <h3>Création/modification/suppression de compte</h3>

    <ul>
        <li>Un utilisateur doit pouvoir créer un compte sur Tweetr facilement. Afin de créer un compte; un nom,
            adresse mail, password et username est requis.
        </li>
        <li>Cet utilisateur peut facilement accéder à sa page profil et modifier les informations de son
            profil.
        </li>
        <li>Un utilisateur doit pouvoir uploader une image liée à son profil. C’est cette image qui sera affiché
            à coté de ses twits.
        </li>
        <li>Cet utilisateur doit avoir la possibilité de supprimer son compte.</li>
    </ul>


    <h3>Abonnements</h3>

    <ul>
        <li>Un utilisateur peut facilement rechercher un utilisateur par son nom ou par son username.</li>
        <li><p>Un utilisateur peut suivre ou décider d’arrêter de suivre un utilisateur.</p>

            <ul>
                <li>Si un utilisateur suit d’autres utilisateurs, les twits des ces utilisateurs suivis doivent
                    apparaitre sur sa page principale triés de tels sortes que les plus récents apparaissent en
                    premier.
                </li>
                <li>De plus, il doit apparaitre sur son profil les personnes suivis et par qui l’utilisateur est
                    suivi.
                </li>
            </ul>
        </li>
    </ul>


    <h3>Twits</h3>

    <ul>
        <li>Un utilisateur a une page principale qui affiche ses twits ainsi que les twits des utilisateurs
            suivi
        </li>
        <li>Un utilisateur peut retwiter un twit d’un autre utilisateur. Ainsi, l’utilisateur partage le twit
            avec ses abonné avec la mention « retwit ».
        </li>
    </ul>


    <h3>Espace publique/privé</h3>

    <ul>
        <li>Les twits d’un user sont publiques.</li>
        <li>Le reste de l’application est jugé comme privé. !</li>
    </ul>


    <h2>Détails Pratiques</h2>

    <p>Le projet peut être développé par groupe de 2 étudiants maximum.</p>

    <h3>Délivrables</h3>

    <p>Un rapport de 10 à 15 pages maximum vous est demandé (hors annexe).</p>

    <p>Dans celui-ci, il vous sera demandé:</p>

    <ul>
        <li>d’expliquer vos choix d’architecture, de comment vous avez mis en place votre logique (Séparation
            des fonctionnalités, encapsulation, quelles sont les choix de variables de sessions ou applications
            ...).
        </li>
        <li>d’expliquer l’architecture de votre base de données.</li>
        <li><p>de me donner et d’expliquer 2 scénarios de l’utilisateur.</p>

            <ul>
                <li>Ces scénarios montre le cheminement des requêtes depuis le navigateur web de l’utilisateur
                    vers le serveur jusqu’à la réponse reçue par celui-ci depuis ce même serveur.
                </li>
                <li>Ces scénarios doivent correspondre à une action principale d’un utilisateur avec une
                    interaction sur la base de données.
                </li>
                <li>Ces 2 scénarios doivent être assez différent l’un de l’autre.</li>
            </ul>
        </li>
    </ul>

    <div class="footnotes">
        <hr/>
        <ol>
            <li id="fn:1">
                <p>Twitter - <a href="http://www.twitter.com">http://www.twitter.com</a><a href="#fnref:1"
                                                                                           rev="footnote">
                    &#8617;</a></p></li>
        </ol>
    </div>
</div>