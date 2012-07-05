<jsp:include page="common/header.jsp"/>
<jsp:include page="common/menu.jsp"/>

<div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit">
        <h1>Hello, world!</h1>
    </div>

    <h1>Students list:</h1>

    <table>
        <thead>
        <tr>
            <th>Student Name</th>
            <th>Age</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${name}</td>
            <td>${age}</td>
        </tr>
        </tbody>
    </table>

</div>

<jsp:include page="common/scripts.jsp"/>