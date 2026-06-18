<template>
    <div>
        <div class="search-button">
            <el-input
                clearable
                v-model="searchInfo"
                @clear="search()"
                suffix-icon="Search"
                @change="search()"
                :placeholder="props.placeholder || $t('commons.button.search')"
            ></el-input>
        </div>
    </div>
</template>

<script >
import { ref } from 'vue';

const searchInfo = ref();
const props = defineProps({
    placeholder: String,
});

const search = () => {
    emit('update:searchName', searchInfo.value);
    emit('search');
};
</script> 
