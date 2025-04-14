Vue.component('administrative-personnel-view', {
    template: `
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-5">
                <div style="margin-left:200px;margin-bottom: 20px;">
                    <img src="/img/ccsd-email-logo.png" alt="Logo" width="120" height="80" >
               </div>
                    <div style="margin-left:200px;" v-if="administrativePersonnel">
                        <h1 style="color: #1771B7;font-size: 32px;font-weight:bold;padding-bottom: 25px;">{{ administrativePersonnel.jobTitle }}</h1>
                        <h2 style="color: #214A7E;font-size: 20px;font-weight:bold;">Position Details</h2>
                        Job Code: {{ administrativePersonnel.jobCode }}<br>
                        Reference Code: {{ administrativePersonnel.referenceCode }}<br>
                        Division/Unit: {{ administrativePersonnel.divisionUnit }}<br>
                        Classification: {{ administrativePersonnel.classification }}<br>
                        Terms of Employment: <span v-html="administrativePersonnel.termsOfEmployment"></span><br>
                        FLSA Status: {{ administrativePersonnel.flsaStatus }}
                        <hr>
                        <h3  style="color: #214A7E;font-size: 20px;font-weight:bold;">Position Summary:</h3>
                        <span v-html="administrativePersonnel.positionSummary"></span>
<hr>
                        <h3  style="color: #214A7E;font-size: 20px;font-weight:bold;">Essential Duties and Responsibilities:</h3>
                        <span v-html="administrativePersonnel.essentialDutiesAndResponsibilities"></span>
                        <hr>
                        <h3  style="color: #214A7E;font-size: 20px;font-weight:bold;">Position Expectations:</h3>
                        <span v-html="administrativePersonnel.positionExpectations"></span>
                        <hr>
                        <h3 style="color: #214A7E;font-size: 20px;font-weight:bold;">Position Requirements:</h3>
                        <span v-html="administrativePersonnel.positionRequirements"></span>
                        <hr>
                        <h3 style="color: #214A7E;font-size: 20px;font-weight:bold;">AA/EOE Statement</h3>
                        <p>The Clark County School District is proud to be an equal opportunity employer. The
Clark County School District is committed to providing all applicants and employees
equal employment opportunities without regard to race, color, religion, sex, gender
identity or expression, sexual orientation, national origin, genetics, disability, age, military
status, or other characteristics protected by applicable law. Here at Clark County School
District, we are a diverse group of people who honor the differences that drive innovative
solutions to meet the needs of our students and employees. We believe that through a
culture of inclusivity, we have the power to reflect the community we serve.</p>
<hr>
                        <h3 style="color: #214A7E;font-size: 20px;font-weight:bold;">Job Revision Information</h3>
                          Created At: {{ formattedCreatedAt }}<br>
                          Updated At: {{ formattedUpdatedAt }}
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
    computed: {
        formattedCreatedAt() {
            if (this.administrativePersonnel && this.administrativePersonnel.createdAt) {
                return new Intl.DateTimeFormat('en-US', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit'
                }).format(new Date(this.administrativePersonnel.createdAt));
            }
            return '';
        },
        formattedUpdatedAt() {
            if (this.administrativePersonnel && this.administrativePersonnel.updatedAt) {
                return new Intl.DateTimeFormat('en-US', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit'
                }).format(new Date(this.administrativePersonnel.updatedAt));
            }
            return '';
        }
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