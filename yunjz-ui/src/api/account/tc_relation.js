import request from '@/utils/request'

// 查询属性&属性分组关联列表
export function listRelation(typeId) {
  return request({
    url: `/account/type/${typeId}/relation/list`,
    method: 'get',
  })
}

// 查询点击新建关联时可以关联的category列表
export function listNoRelationCategory(query, typeId) {
  return request({
    url: `/account/type/${typeId}/no_relation/list`,
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRelation(id) {
  return request({
    url: '/account/tc_relation/' + id,
    method: 'get'
  })
}

// 新增类型&类别关联
export function addRelation(data) {
  return request({
    url: '/account/type/add/relation',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRelation(data) {
  return request({
    url: '/account/tc_relation',
    method: 'put',
    data: data
  })
}

// 删除类型&类别关联关系
export function delRelation(data) {
  return request({
    url: '/account/type/relation/delete',
    method: 'post',
    data: data,
  })
}
