<template>

    <v-data-table
        :headers="headers"
        :items="readConsultingList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReadConsultingListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            readConsultingList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/readConsultingLists'))

            temp.data._embedded.readConsultingLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.readConsultingList = temp.data._embedded.readConsultingLists;
        },
        methods: {
        }
    }
</script>

