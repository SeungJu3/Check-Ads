<template>

    <v-data-table
        :headers="headers"
        :items="readReservationList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReadReservationListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            readReservationList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/readReservationLists'))

            temp.data._embedded.readReservationLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.readReservationList = temp.data._embedded.readReservationLists;
        },
        methods: {
        }
    }
</script>

