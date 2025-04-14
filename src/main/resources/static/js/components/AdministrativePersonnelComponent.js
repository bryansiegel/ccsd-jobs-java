// src/main/resources/static/js/components/AdministrativePersonnelComponent.js
Vue.component('administrative-personnel', {
    template: `
                <div class="container">
            <div class="row">
                <div class="col-md-12 mb-5">
            <h1>Administrative Personnel</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Job Title</th>
                    <th>Job Code</th>
                    <th>Reference Code</th>
                    <th>Division/Unit</th>
                    <th>Classification</th>
<!--                    <th>Terms of Employment</th>-->
<!--                    <th>FLSA Status</th>-->
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="admin in administrativePersonnel" :key="admin.id">
                    <td>{{ admin.jobTitle }}</td>
                    <td>{{ admin.jobCode }}</td>
                    <td>{{ admin.referenceCode }}</td>
                    <td>{{ admin.divisionUnit }}</td>
                    <td>{{ admin.classification }}</td>
<!--                    <td>{{ admin.termsOfEmployment }}</td>-->
<!--                    <td>{{ admin.flsaStatus }}</td>-->
                    <td><a :href="'/administrative-personnel/' + admin.id" class="btn btn-info">View Public Job</a></td>
                </tr>
                </tbody>
            </table>
        </div>
        </div>
        </div>
    `,
    data() {
        return {
            administrativePersonnel: []
        };
    },
    created() {
        axios.get('/api/administrative-personnel', {
            auth: {
                username: 'user',
                password: 'password'
            }
        })
            .then(response => {
                this.administrativePersonnel = response.data;
            })
            .catch(error => {
                console.error('There was an error fetching the data:', error);
            });
    }
});