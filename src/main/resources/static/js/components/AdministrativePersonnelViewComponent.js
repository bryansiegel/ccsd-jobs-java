// src/main/resources/static/js/components/AdministrativePersonnelViewComponent.js
Vue.component('administrative-personnel-view', {
    template: `
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div style="margin-left:200px;" v-if="administrativePersonnel">
                        <h1>{{ administrativePersonnel.jobTitle }}</h1>
                        <h3>Job Code:</h3>
                        <span>{{ administrativePersonnel.jobCode }}</span>
                        <h3>Reference Code:</h3>
                        <span>{{ administrativePersonnel.referenceCode }}</span>
                        
                        <h3>Division/Unit:</h3>
                        <span>{{ administrativePersonnel.divisionUnit }}</span>
                        
                        <h3>Classification:</h3>
                        <span>{{ administrativePersonnel.classification }}</span>
                        
                        <h3>Terms of Employment:</h3>
                        <span v-html="administrativePersonnel.termsOfEmployment"></span>

                        <h3>FLSA Status:</h3>
                        <span> {{ administrativePersonnel.flsaStatus }}</span>
                        
                        <h3>Position Summary:</h3>
                        <span v-html="administrativePersonnel.positionSummary"></span>

                        <h3>Essential Duties and Responsibilities:</h3>
                        <span v-html="administrativePersonnel.essentialDutiesAndResponsibilities"></span>

                        <h3>Position Expectations:</h3>
                        <span v-html="administrativePersonnel.positionExpectations"></span>
                        <h3>Position Requirements:</h3>
                        <span v-html="administrativePersonnel.positionRequirements"></span>
                    </div>
                    <div v-else>
                        <p>Loading...</p>
                    </div>
                </div>
            </div>
        </div>
    `,
    data() {
        return {
            administrativePersonnel: null
        };
    },
    created() {
        const id = window.location.pathname.split('/').pop();
        axios.get(`/api/administrative-personnel/${id}`, {
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