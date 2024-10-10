<template>
    <v-card style="width:450px; height:100%;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            User # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            User
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String label="Name" v-model="value.name" :editMode="editMode" :inputUI="''"/>
            <String label="Field" v-model="value.field" :editMode="editMode" :inputUI="''"/>
            <String label="CompName" v-model="value.compName" :editMode="editMode" :inputUI="''"/>
            <String label="CompNo" v-model="value.compNo" :editMode="editMode" :inputUI="''"/>
            <String label="CompTel" v-model="value.compTel" :editMode="editMode" :inputUI="''"/>
            <String label="CompAddr" v-model="value.compAddr" :editMode="editMode" :inputUI="''"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            
            <h4>Consulting Schedule</h4>
            <br>
            <v-list>
                <v-list-item-group>
                    <v-list-item v-for="consulting in consultings" :key="consulting.id">
                        <v-list-item-content>
                            <v-list-item-title>
                                <strong>{{ consulting.date }}</strong>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                Reservation ID: {{ consulting.resId }}
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>

            <v-spacer></v-spacer>

            <v-btn
                color="primary"
                text
                @click="edit"
                v-if="!editMode"
            >
                수정
            </v-btn>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                    SignUp
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
                >
                    삭제
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'UserUser',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            consultings: [
                {
                    'resId' : 0,
                    'date' : "2024-10-10"
                },
                {
                    'resId' : 1,
                    'date' : "2024-10-11"
                }
            ],
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
        }),
        mounted() {
            this.fetchConsultings();
        },
	async created() {
        },
        methods: {
            async fetchConsultings() {
                try {
                    const response = await axios.get("https://8088-seungju3-checkads-bejf89dkv3a.ws-us116.gitpod.io/api/consultings/user/0");
                    this.consultings = response.data;
                    console.log("Fetched consultings:", response.data);
                } catch (error) {
                    console.error("Error fetching consultings:", error);
                    if (error.response) {
                        console.error("Response data:", error.response.data);
                        console.error("Response status:", error.response.status);
                    }
                }
            },
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/users'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
        },
    }
</script>

