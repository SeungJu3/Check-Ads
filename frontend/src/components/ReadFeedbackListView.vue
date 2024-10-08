<template>

    <v-data-table
        :headers="headers"
        :items="readFeedbackList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReadFeedbackListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            readFeedbackList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/readFeedbackLists'))

            temp.data._embedded.readFeedbackLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.readFeedbackList = temp.data._embedded.readFeedbackLists;
        },
        methods: {
        }
    }
</script>

