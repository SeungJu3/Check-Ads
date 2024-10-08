<template>

    <v-data-table
        :headers="headers"
        :items="readReportList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReadReportListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            readReportList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/readReportLists'))

            temp.data._embedded.readReportLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.readReportList = temp.data._embedded.readReportLists;
        },
        methods: {
        }
    }
</script>

