import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listCategory(query) {
  return request({
    url: '/account/category/list',
    method: 'get',
    params: query
  })
}

// 根据typeId获取类别名称列表
export function getCategoryNameListByTypeId(typeId) {
  return request({
    url: '/account/category/categorynamelist/' + typeId,
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getCategory(categoryId) {
  return request({
    url: '/account/category/' + categoryId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addCategory(data) {
  return request({
    url: '/account/category',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateCategory(data) {
  return request({
    url: '/account/category',
    method: 'put',
    data: data
  })
}

// 修改账单类别状态
export function updateCategoryStatus(data) {
  return request({
    url: '/account/category/status',
    method: 'post',
    data: data
  })
}

// 删除【请填写功能名称】
export function delCategory(categoryId) {
  return request({
    url: '/account/category/' + categoryId,
    method: 'delete'
  })
}
