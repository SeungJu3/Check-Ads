<template>

    <v-data-table
        :headers="headers"
        :items="readMemberList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReadMemberListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            readMemberList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/readMemberLists'))

            temp.data._embedded.readMemberLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.readMemberList = temp.data._embedded.readMemberLists;
        },
        methods: {
        }
    }
</script>

